package de.sample.schulung.spring.blog.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
  @Size(min = 3)
  @NotNull
  private String title;
  @Size(min = 10)
  @NotNull
  private String content;
  private LocalDateTime timestamp;

}
