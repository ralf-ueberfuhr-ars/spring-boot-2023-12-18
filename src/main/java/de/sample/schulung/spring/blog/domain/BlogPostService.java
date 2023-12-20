package de.sample.schulung.spring.blog.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class BlogPostService {

  private final Map<UUID, BlogPost> blogPosts = new HashMap<>();
  private final ApplicationEventPublisher eventPublisher;

  public long count() {
    return blogPosts.size();
  }

  public Stream<BlogPost> findAll() {
    return blogPosts.values().stream();
  }

  public Optional<BlogPost> findById(UUID id) {
    return Optional.ofNullable(
      this.blogPosts.get(id)
    );
  }

  public void create(@Valid @NotNull BlogPost blogPost) {
    final var id = UUID.randomUUID();
    blogPost.setId(id);
    blogPost.setTimestamp(LocalDateTime.now());
    this.blogPosts.put(id, blogPost);
    this.eventPublisher.publishEvent(new BlogPostCreatedEvent(blogPost));
  }


}
