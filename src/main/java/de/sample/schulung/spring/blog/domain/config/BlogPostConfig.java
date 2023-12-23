package de.sample.schulung.spring.blog.domain.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogPostConfig {

  private String title;
  private String content;

}
