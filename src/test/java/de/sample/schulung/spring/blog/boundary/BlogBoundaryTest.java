package de.sample.schulung.spring.blog.boundary;

import de.sample.schulung.spring.blog.domain.BlogPostService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

import static org.mockito.Mockito.mock;

// some Java basic stuff
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
// only for test classes
@Target(ElementType.TYPE)
// Spring integration test only for boundary incl. MockMvc
@WebMvcTest
// scan only classes in this package (and sub packages) to find annotated classes
@ComponentScan(basePackageClasses = BlogBoundaryTest.class)
// register beans from the configuration (see below)
@Import(BlogBoundaryTest.BlogBoundaryTestConfiguration.class)
// just to allow special configuration for these tests
//  we could configure in
// - application-test.yml for all tests
// - application-test-boundary.yml especially for these tests
@ActiveProfiles({"test", "test-boundary"})
// just to allow executing Maven running only these tests
// run "mvc surefire:test -Dgroups=boundary-integration-test"
@Tag("integration-test")
@Tag("boundary-integration-test")
public @interface BlogBoundaryTest {

  @TestConfiguration
  class BlogBoundaryTestConfiguration {
    // we register mocks here, that we might need for tests

    @Bean
    BlogPostService blogPostService() {
      return mock(
        BlogPostService.class,
        // reset the mock instructions and verifications between the tests
        MockReset.withSettings(MockReset.AFTER)
      );
    }

  }

}
