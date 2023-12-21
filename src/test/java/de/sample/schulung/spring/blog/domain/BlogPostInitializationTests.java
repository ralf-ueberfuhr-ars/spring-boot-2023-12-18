package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.comparator.Comparators;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * THIS WILL LEAD TO A SEPARATE CONTEXT !!!
 */
@SpringBootTest
// komplette Anwendung -> Test der Konfiguration in application.yml
@TestPropertySource(properties = {
  "application.initialization.enabled=true",
  "application.initialization.blogposts[0].title=test1",
  "application.initialization.blogposts[0].content=content123", // at least 10 chars
  "application.initialization.blogposts[1].title=test2",
  "application.initialization.blogposts[1].content=content234", // at least 10 chars
})
public class BlogPostInitializationTests {

  @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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
