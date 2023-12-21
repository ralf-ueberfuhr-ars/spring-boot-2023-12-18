package de.sample.schulung.spring.blog.domain.config;

import de.sample.schulung.spring.blog.domain.BlogPost;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogPostConfigMapper {

  BlogPost map(BlogPostConfig source);

}
