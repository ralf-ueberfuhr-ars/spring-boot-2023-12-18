package de.sample.schulung.spring.blog.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface BlogPostRepository
  extends JpaRepository<BlogPostEntity, UUID> {

  // oder @Query("...")
  Stream<BlogPostEntity> streamBlogPostEntitiesByTitleContainingIgnoreCaseOrderByTimestampDesc(String title);

}
