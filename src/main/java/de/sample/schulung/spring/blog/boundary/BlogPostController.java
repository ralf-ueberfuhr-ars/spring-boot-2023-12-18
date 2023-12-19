package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPost;
import de.sample.schulung.spring.blog.domain.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/blogposts")
@RequiredArgsConstructor
public class BlogPostController {

  private final BlogPostService service;

  @GetMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  Stream<BlogPostDto> findAllBlogPosts() {
    return service.findAll()
      .map(post -> {
        BlogPostDto dto = new BlogPostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setTimestamp(post.getTimestamp());
        return dto;
      });
  }

  @GetMapping(
    value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  BlogPostDto findById(@PathVariable UUID id) {
    return service.findById(id)
      .map(post -> {
        BlogPostDto dto = new BlogPostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setTimestamp(post.getTimestamp());
        return dto;
      })
      .orElse(null);
  }

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<BlogPostDto> createBlogPost(@RequestBody BlogPostDto blogPostDto) {
    final var post = BlogPost.builder()
      .title(blogPostDto.getTitle())
      .content(blogPostDto.getContent())
      .build();
    service.create(post);
    blogPostDto.setId(post.getId());
    blogPostDto.setTimestamp(post.getTimestamp());
    // Maven Dependency: spring-boot-starter-hateoas
    final var location = linkTo(
      methodOn(BlogPostController.class)
        .findById(post.getId())
    ).toUri();
    return ResponseEntity
      .created(location)
      .body(blogPostDto);
  }

}
