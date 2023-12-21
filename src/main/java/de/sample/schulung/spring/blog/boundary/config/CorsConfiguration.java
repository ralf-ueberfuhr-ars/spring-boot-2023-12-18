package de.sample.schulung.spring.blog.boundary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static java.util.Arrays.stream;

@SuppressWarnings("NullableProblems")
@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer(final CorsConfigurationData allowed) {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(final CorsRegistry registry) {
                registry.addMapping("/**")
                  .exposedHeaders(
                    HttpHeaders.LOCATION,
                    HttpHeaders.LINK
                  )
                  // allow all HTTP request methods
                  .allowedMethods(
                    stream(RequestMethod.values())
                      .map(Enum::name)
                      .toArray(String[]::new)
                  )
                  // allow the commonly used headers
                  .allowedHeaders(
                    HttpHeaders.ORIGIN,
                    HttpHeaders.CONTENT_TYPE,
                    HttpHeaders.CONTENT_LANGUAGE,
                    HttpHeaders.ACCEPT,
                    HttpHeaders.ACCEPT_LANGUAGE,
                    HttpHeaders.IF_MATCH,
                    HttpHeaders.IF_NONE_MATCH
                  )
                  // this is stage specific
                  .allowedOrigins(allowed.getOrigins())
                  .allowCredentials(allowed.isCredentials());
            }
        };
    }

}
