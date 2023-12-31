package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPost;
import de.sample.schulung.spring.blog.domain.BlogPostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@BlogBoundaryTest
class BlogPostApiWithMockedServiceTests {

  @Autowired
  MockMvc mvc;
  @Autowired // this is a mock!
  BlogPostService serviceMock;


  /*
   * Testfall:
   *  - Lege BlogPost an (title: "test")
   *  - Frage BlogPost ab und prüfe, ob Titel == "test"
   */

  @Test
  void shouldReturnSingleBlogPostSuccessfully() throws Exception {
    final var id = UUID.randomUUID();
    final var samplePost = BlogPost.builder()
      .id(id)
      .title("test")
      .timestamp(LocalDateTime.now())
      .build();
    // Setup
    when(serviceMock.findById(id))
      .thenReturn(Optional.of(samplePost));

    // test: request blog post
    mvc.perform(
        get("/blogposts/{id}", id)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title").value("test"))
      .andExpect(jsonPath("$.id").value(id.toString()))
      .andExpect(jsonPath("$.timestamp").exists());
  }

  @Test
  void shouldNotCreateInvalidBlogPost() throws Exception {
    mvc.perform(
        post("/blogposts")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
              {
               "content": "Das ist ein Test"
              }
            """)
      )
      .andExpect(status().isBadRequest());
    verifyNoInteractions(serviceMock);

  }


  // TODO wenn ID im Request mitgeschickt wird -> ??

}
