package de.sample.schulung.spring.blog;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

  @GetMapping(
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  String sayHello() {
    return "Hello World";
  }

  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.CREATED)
  BlogPostDto createBlogPost(@RequestBody BlogPostDto blogPostDto) {
    blogPostDto.setId(UUID.randomUUID());
    blogPostDto.setTimestamp(LocalDateTime.now());
    return blogPostDto;
  }

}
