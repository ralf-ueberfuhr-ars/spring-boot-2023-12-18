package de.sample.schulung.spring.blog.domain;

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

  @EventListener(ContextRefreshedEvent.class)
  void initBlogPosts() {
    if(service.count() == 0) {
      service.create(
        BlogPost
          .builder()
          .title("Welcome to the blog!")
          .content("This is a great blog :)")
          .build()
      );
    }

  }

}
