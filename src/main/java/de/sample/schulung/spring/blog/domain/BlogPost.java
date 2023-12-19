package de.sample.schulung.spring.blog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class BlogPost {

  private UUID id;
  private String title;
  private String content;
  private LocalDateTime timestamp;

}
