package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@BlogDomainTest
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
