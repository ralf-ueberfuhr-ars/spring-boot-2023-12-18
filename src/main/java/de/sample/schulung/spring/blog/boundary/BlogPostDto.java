package de.sample.schulung.spring.blog.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BlogPostDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID id;
  private String title;
  private String content;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime timestamp;

}
