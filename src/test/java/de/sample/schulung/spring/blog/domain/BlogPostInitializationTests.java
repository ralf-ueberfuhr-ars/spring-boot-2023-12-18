package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@BlogDomainTest
/*
 * THIS WILL LEAD TO A SEPARATE CONTEXT !!!
 */
@TestPropertySource(properties = {
  "application.initialization.enabled=true",
  "application.initialization.blogposts[0].title=test1",
  "application.initialization.blogposts[0].content=content123", // at least 10 chars
  "application.initialization.blogposts[1].title=test2",
  "application.initialization.blogposts[1].content=content234", // at least 10 chars
})
public class BlogPostInitializationTests {

  @Autowired
  BlogPostService service;

  @Test
  void shouldNotBeEmpty() {
    assertThat(service.count())
      .isEqualTo(2);
    assertThat(service.findAll().sorted(Comparator.comparing(BlogPost::getTitle)))
      .extracting(BlogPost::getTitle)
      .containsExactly("test1", "test2");
  }

}
