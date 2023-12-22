package de.sample.schulung.spring.blog.domain.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class PublishEventInterceptor
  extends AbstractBeanFactoryAwareAdvisingPostProcessor
  implements InitializingBean {

  private final MethodInterceptor publishEventAdvice;

  public PublishEventInterceptor(final ApplicationEventPublisher eventPublisher) {
    this.publishEventAdvice = invocation -> {
      // create event object
      Object event = null;
      // find @PublishEvent annotation on invoked method
      final var annotation = AnnotationUtils.findAnnotation(invocation.getMethod(), PublishEvent.class);
      if(null != annotation) {
        event = annotation.value() // event type
          // event type must have a constructor with the same type as the invoked method
          // service.create(blogPost) -> new BlogPostCreatedEvent(blogPost)
          .getConstructor(invocation.getMethod().getParameterTypes())
          .newInstance(invocation.getArguments());
      }
      // if something is wrong until here, we do not invoke the service's create-method
      // now, we invoke the service
      final var result = invocation.proceed();
      // if an exception occured, the event is not fired
      // now, we fire the event
      if(null != event) {
        eventPublisher.publishEvent(event);
      }
      // and we need to return the service's result to the invoker (the controller)
      return result;
    };
  }

  @Override
  public void afterPropertiesSet() {
    Pointcut pointcut = new AnnotationMatchingPointcut(PublishEvent.class, true);
    this.advisor = new DefaultPointcutAdvisor(pointcut, publishEventAdvice);
  }


}
