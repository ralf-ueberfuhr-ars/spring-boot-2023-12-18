package de.sample.schulung.spring.blog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blogposts")
public class BlogPostController {

  @GetMapping
  @ResponseBody
  String sayHello() {
    return "Hello World";
  }

}
