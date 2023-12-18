package de.sample.schulung.spring.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

  /*
   * Testfall:
   *  - Lege BlogPost an (title: "test")
   *  - Frage BlogPost ab und prüfe, ob Titel == "test"
   */

  @Test
  void shouldReturn406WhenGetBlogPostsWithInvalidMediaType() throws Exception {
    mvc.perform(
        get("/blogposts")
          .accept(MediaType.APPLICATION_XML)
      )
      .andExpect(
        status().isNotAcceptable()
      );
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
      .andExpect(jsonPath("$.timestamp").exists());
    // .title = "test"
    // Location-Header
  }

  // TODO wenn ID im Request mitgeschickt wird -> ??

}
