package de.sample.schulung.spring.blog.persistence;

import de.sample.schulung.spring.blog.domain.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BlogPostEntityMapper {

  BlogPost map(BlogPostEntity source);

  BlogPostEntity map(BlogPost source);

  void copy(BlogPostEntity source, @MappingTarget BlogPost target);

}
