package de.sample.schulung.spring.blog.domain;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryBlogPostSinkConfiguration {

  @Bean
  @ConditionalOnMissingBean(BlogPostSink.class)
  BlogPostSink inMemorySink() {
    return new InMemoryBlogPostSink();
  }

}
