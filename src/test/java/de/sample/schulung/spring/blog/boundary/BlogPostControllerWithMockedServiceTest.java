package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPost;
import de.sample.schulung.spring.blog.domain.BlogPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogPostControllerWithMockedServiceTest {

  @Mock
  BlogPostService service;

  @InjectMocks
  BlogPostController sut;

  @Test
  void findByIdShouldReturnBlogPost() {

    final var id = UUID.randomUUID();
    final var samplePost = BlogPost.builder()
      .id(id)
      .title("test")
      .build();
    // Setup
    when(service.findById(id))
      .thenReturn(Optional.of(samplePost));
    // Test
    final var result = sut.findById(id);
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(id);
  }

}
