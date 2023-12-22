package de.sample.schulung.spring.blog.persistence;

import de.sample.schulung.spring.blog.domain.BlogPost;
import de.sample.schulung.spring.blog.domain.BlogPostSink;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ComponentScan(basePackageClasses = JpaBlogPostSinkTests.class)
public class JpaBlogPostSinkTests {

  @Autowired
  BlogPostSink sink;

  /*
   * Testfall:
   *  - Erzeugen eines Blogposts -> Liste der BlogPosts: Länge=1 mit dem einem BlogPost
   */
  @Test
  void shouldReturnCreatedBlogPost() {
    final var post = BlogPost.builder()
      .title("test")
      .content("Das ist der Content")
      .timestamp(LocalDateTime.now())
      .build();
    sink.create(post);
    assertThat(post.getId())
      .isNotNull();
    assertThat(sink.findAll())
      .hasSize(1)
      .first()
      .extracting(BlogPost::getId)
      .isEqualTo(post.getId());
  }

}
