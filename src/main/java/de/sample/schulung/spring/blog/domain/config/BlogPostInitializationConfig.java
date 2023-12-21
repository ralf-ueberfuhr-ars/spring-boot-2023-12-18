package de.sample.schulung.spring.blog.domain.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties("application.initialization")
public class BlogPostInitializationConfig {

  private boolean enabled = true;
  private List<BlogPostConfig> blogposts = new LinkedList<>();

}
