package de.sample.schulung.spring.blog.domain;

import de.sample.schulung.spring.blog.domain.config.BlogPostConfigMapper;
import de.sample.schulung.spring.blog.domain.config.BlogPostInitializationConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
@RequiredArgsConstructor
@Profile("!no-initialization")
@Slf4j
public class BlogPostInitializer {

  private final BlogPostService service;
  private final BlogPostInitializationConfig config;
  private final BlogPostConfigMapper mapper;

  @EventListener(ContextRefreshedEvent.class)
  void initBlogPosts() {
    log.debug("Initializing blogposts with config: {}", config);
    if(service.count() == 0 && config.isEnabled()) {
      config.getBlogposts()
        .stream()
        .map(this.mapper::map)
        .forEach(service::create);
    }

  }

}
