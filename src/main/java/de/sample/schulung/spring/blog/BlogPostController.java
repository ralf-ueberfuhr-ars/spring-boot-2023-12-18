package de.sample.schulung.spring.blog;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

// Maven Dependency: spring-boot-starter-hateoas
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

  private final Map<UUID, BlogPostDto> blogPosts = new HashMap<>();

  @GetMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  Stream<BlogPostDto> findAllBlogPosts() {
    return blogPosts.values().stream();
  }

  @GetMapping(
    value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  BlogPostDto findById(@PathVariable UUID id) {
    return this.blogPosts.get(id);
  }

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<BlogPostDto> createBlogPost(@RequestBody BlogPostDto blogPostDto) {
    final var id = UUID.randomUUID();
    blogPostDto.setId(id);
    blogPostDto.setTimestamp(LocalDateTime.now());
    this.blogPosts.put(id, blogPostDto);
    // Maven Dependency: spring-boot-starter-hateoas
    final var location = linkTo(
      methodOn(BlogPostController.class)
        .findById(id)
    ).toUri();
    return ResponseEntity
      .created(location)
      .body(blogPostDto);
  }

}
