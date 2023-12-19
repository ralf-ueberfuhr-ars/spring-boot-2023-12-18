package de.sample.schulung.spring.blog.domain;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class BlogPostService {

  private final Map<UUID, BlogPost> blogPosts = new HashMap<>();

  public Stream<BlogPost> findAll() {
    return blogPosts.values().stream();
  }

  public Optional<BlogPost> findById(UUID id) {
    return Optional.ofNullable(
      this.blogPosts.get(id)
    );
  }

  public void create(BlogPost blogPost) {
    final var id = UUID.randomUUID();
    blogPost.setId(id);
    blogPost.setTimestamp(LocalDateTime.now());
    this.blogPosts.put(id, blogPost);
  }


}
