package de.sample.schulung.spring.blog.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BlogPostDto {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UUID id;
  @NotNull
  @Size(min = 3)
  private String title;
  @NotNull
  @Size(min = 10)
  private String content;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime timestamp;

}
