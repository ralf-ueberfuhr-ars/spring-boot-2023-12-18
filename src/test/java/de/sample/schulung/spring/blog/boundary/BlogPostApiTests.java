package de.sample.schulung.spring.blog.boundary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BlogPostApiTests {

  @Autowired
  MockMvc mvc;

  @Test
  void shouldReturn200WhenGetBlogPosts() throws Exception {
    mvc.perform(
        get("/blogposts")
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void shouldReturn406WhenGetBlogPostsWithInvalidMediaType() throws Exception {
    mvc.perform(
        get("/blogposts")
          .accept(MediaType.APPLICATION_XML)
      )
      .andExpect(status().isNotAcceptable());
  }

  // TODO wenn zu komplex -> @Nested

  @Test
  void shouldCreateBlogPostSuccessfully() throws Exception {
    mvc.perform(
        post("/blogposts")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .content("""
              {
               "title": "test",
               "content": "Das ist ein Test"
              }
            """)
      )
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.title").value("test"))
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.timestamp").exists())
      .andExpect(header().exists(HttpHeaders.LOCATION));

  }

  /*
   * Testfall:
   *  - Lege BlogPost an (title: "test")
   *  - Frage BlogPost ab und prüfe, ob Titel == "test"
   */

  @Test
  void shouldReturnSingleBlogPostSuccessfully() throws Exception {
    // setup: create blog post
    final var newBlogPostLocation =
      mvc.perform(
          post("/blogposts")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                 "title": "test",
                 "content": "Das ist ein Test"
                }
              """)
        )
        .andReturn()
        .getResponse()
        .getHeader(HttpHeaders.LOCATION);
    assertThat(newBlogPostLocation).isNotEmpty();
    // test: request blog post
    mvc.perform(
        get(newBlogPostLocation)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title").value("test"))
      .andExpect(jsonPath("$.id").exists())
      .andExpect(jsonPath("$.timestamp").exists());
  }


  // TODO wenn ID im Request mitgeschickt wird -> ??

}
