package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPostService;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class BlogPostControllerTests {

  @Test
  void shouldCreateBlogPostSuccessfully() {
    final var service = new BlogPostService();
    final var mapper = Mappers.getMapper(BlogPostDtoMapper.class);
    final var controller = new BlogPostController(service, mapper);
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
