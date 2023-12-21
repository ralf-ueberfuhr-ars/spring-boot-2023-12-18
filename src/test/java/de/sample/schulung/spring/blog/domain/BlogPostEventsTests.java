package de.sample.schulung.spring.blog.domain;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.ApplicationEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@BlogDomainTest
public class BlogPostEventsTests {

  @Autowired
  BlogPostService service;
  @Autowired
  ApplicationEvents events;

  @Test
  void shouldPublishEventOnCreate() {
    final var newBlogPost = BlogPost
      .builder()
      .title("test")
      .content("test content with 10 chars")
      .build();
    service.create(newBlogPost);

    assertThat(events.stream(BlogPostCreatedEvent.class))
      .hasSize(1)
      .first()
      .extracting(BlogPostCreatedEvent::blogPost)
      .isSameAs(newBlogPost);
  }

  @Test
  void shouldNotPublishEventOnInvalidCreate() {
    final var newBlogPost = BlogPost
      .builder()
      .content("test content with 10 chars")
      .build();
    assertThatThrownBy(() -> service.create(newBlogPost))
      .isInstanceOf(ConstraintViolationException.class);
    assertThat(events.stream(BlogPostCreatedEvent.class))
      .isEmpty();
  }
}
