package de.sample.schulung.spring.blog.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BlogPostEventLogger {

  @EventListener
  void logBlogPostCreated(BlogPostCreatedEvent evt) {
    log.debug("BlogPost created: {}", evt.blogPost());
  }

}
