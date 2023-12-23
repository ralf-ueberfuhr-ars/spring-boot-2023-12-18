package de.sample.schulung.spring.blog.domain;

import de.sample.schulung.spring.blog.BlogApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@BlogApplicationTest
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
