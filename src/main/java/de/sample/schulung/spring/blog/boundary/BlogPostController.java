package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/blogposts")
@RequiredArgsConstructor
public class BlogPostController {

  private final BlogPostService service;
  private final BlogPostDtoMapper mapper;

  @GetMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  Stream<BlogPostDto> findAllBlogPosts() {
    return service.findAll()
      .map(mapper::map);
  }

  @GetMapping(
    value = "/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  BlogPostDto findById(@PathVariable UUID id) {
    return service.findById(id)
      .map(mapper::map)
      .orElse(null);
  }

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  ResponseEntity<BlogPostDto> createBlogPost(
    @Valid
    @RequestBody
    BlogPostDto blogPostDto
  ) {
    final var post = mapper.map(blogPostDto);
    service.create(post);
    mapper.copy(post, blogPostDto);
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
