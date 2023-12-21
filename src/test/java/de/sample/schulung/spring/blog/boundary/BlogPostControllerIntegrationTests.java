package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.BlogApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

// SO NICHT!
@BlogApplicationTest
public class BlogPostControllerIntegrationTests {

  @Autowired
  BlogPostController controller;

  @Test
  void shouldCreateBlogPostSuccessfully() {
    final var blogPost = new BlogPostDto();
    blogPost.setTitle("test");
    blogPost.setContent("Das ist ein Test");

    final var result = controller.createBlogPost(blogPost);

    assertThat(result.getStatusCode().value())
      .isEqualTo(HttpStatus.CREATED.value());
    assertThat(result.getBody())
      .isNotNull();
    assertThat(result.getBody().getTitle())
      .isEqualTo(blogPost.getTitle());

  }
}
