package de.sample.schulung.spring.blog.domain;

import de.sample.schulung.spring.blog.domain.interceptors.PublishEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class BlogPostService {

  private final BlogPostSink sink;

  public long count() {
    return sink.count();
  }

  public Stream<BlogPost> findAll() {
    return sink.findAll();
  }

  public Optional<BlogPost> findById(UUID id) {
    return sink.findById(id);
  }

  @PublishEvent(BlogPostCreatedEvent.class)
  public void create(@Valid @NotNull BlogPost blogPost) {
    blogPost.setTimestamp(LocalDateTime.now());
    this.sink.create(blogPost);
  }

}
