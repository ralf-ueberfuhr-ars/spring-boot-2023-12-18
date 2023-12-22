package de.sample.schulung.spring.blog.domain;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface BlogPostSink {

  long count();
  void create(BlogPost post);
  Stream<BlogPost> findAll();
  Optional<BlogPost> findById(UUID id);

  default void createAll(BlogPost... blogposts) {
    Stream.of(blogposts)
      .forEach(this::create);
  }

}
