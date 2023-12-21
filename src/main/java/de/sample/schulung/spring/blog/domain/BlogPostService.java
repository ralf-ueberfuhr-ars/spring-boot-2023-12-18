package de.sample.schulung.spring.blog.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Validated
@Service
@RequiredArgsConstructor
public class BlogPostService {

  private final ApplicationEventPublisher eventPublisher;
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

  public void create(@Valid @NotNull BlogPost blogPost) {
    this.sink.create(blogPost);
    this.eventPublisher.publishEvent(new BlogPostCreatedEvent(blogPost));
  }


}
