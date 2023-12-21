package de.sample.schulung.spring.blog.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "BlogPost") // Name der Entity (JPQL)
@Table(name = "BLOG_POSTS")
public class BlogPostEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Size(min = 3)
  @NotNull
  private String title;
  @Size(min = 10)
  @NotNull
  private String content;
  @Column(name = "TIME_STAMP")
  private LocalDateTime timestamp;

}
