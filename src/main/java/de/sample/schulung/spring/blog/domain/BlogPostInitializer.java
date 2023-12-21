package de.sample.schulung.spring.blog.domain;

import de.sample.schulung.spring.blog.domain.config.BlogPostInitializationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("!no-initialization")
public class BlogPostInitializer {

  private final BlogPostService service;
  private final BlogPostInitializationConfig config;

  @EventListener(ContextRefreshedEvent.class)
  void initBlogPosts() {
    if(service.count() == 0 && config.isEnabled()) {
      config.getBlogposts()
          .forEach(
            blogPostConfig -> {
              var blogpost = BlogPost
                .builder()
                .title(blogPostConfig.getTitle())
                .content(blogPostConfig.getContent())
                .build();
              service.create(blogpost);
            }
          );
    }

  }

}
