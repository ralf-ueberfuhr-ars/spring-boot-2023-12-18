package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BlogPostServiceTests {

  @Autowired
  BlogPostService service;

  @Test
  void shouldNotBeEmpty() {
    assertThat(service.count())
      .isGreaterThan(0L);
    assertThat(service.findAll())
      .hasAtLeastOneElementOfType(BlogPost.class);
  }

}
