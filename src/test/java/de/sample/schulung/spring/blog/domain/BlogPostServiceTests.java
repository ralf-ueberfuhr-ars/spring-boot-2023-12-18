package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
// komplette Anwendung -> Test der Konfiguration in application.yml
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
