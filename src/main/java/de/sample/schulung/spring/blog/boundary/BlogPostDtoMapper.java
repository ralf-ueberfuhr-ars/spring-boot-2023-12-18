package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPost;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BlogPostDtoMapper {

  BlogPost map(BlogPostDto source);

  BlogPostDto map(BlogPost source);

  void copy(BlogPost post, @MappingTarget BlogPostDto blogPostDto);
}
