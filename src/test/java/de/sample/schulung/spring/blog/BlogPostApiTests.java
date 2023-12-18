package de.sample.schulung.spring.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
      .andExpect(
        status().isOk()
      );
  }

}
