package de.sample.schulung.spring.blog.persistence;

import de.sample.schulung.spring.blog.domain.BlogPost;
import de.sample.schulung.spring.blog.domain.BlogPostSink;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class JpaBlogPostSink implements BlogPostSink {

  private final BlogPostRepository repo;
  private final BlogPostEntityMapper mapper;

  @Override
  public long count() {
    return repo.count();
  }

  @Override
  public void create(BlogPost post) {
    final var entity = mapper.map(post);
    final var savedEntity = repo.save(entity);
    //post.setId(savedEntity.getId());
    mapper.copy(savedEntity, post);
  }

  @Override
  public Stream<BlogPost> findAll() {
    return repo.findAll()
      .stream()
      .map(mapper::map);
  }

  @Override
  public Optional<BlogPost> findById(UUID id) {
    return repo.findById(id)
      .map(mapper::map);
  }
}
