package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.springframework.boot.test.mock.mockito.MockReset.withSettings;

// some Java basic stuff
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
// only for test classes
@Target(ElementType.TYPE)
// we do not want to scann all packages, so we need to specify a root context class
// otherwise, the Application class is used
@SpringBootTest(classes = BlogDomainTest.BlogDomainContext.class)
// scan classes in the boundary package (and sub packages) to find annotated classes
@ComponentScan(basePackageClasses = BlogDomainTest.class)
// Disable all auto-configurations except validation and configuration properties
// disabled for example: DB, Web, Actuator --> faster
@OverrideAutoConfiguration(enabled = false)
@ImportAutoConfiguration({
  ConfigurationPropertiesAutoConfiguration.class,
  ValidationAutoConfiguration.class
})
// register further beans
@Import(BlogDomainTest.BlogDomainTestConfiguration.class)
// we need to reset the in-memory implementation
@ExtendWith(BlogDomainTest.BlogPostSinkResetExtension.class)
// we record events because the domain publishes them
@RecordApplicationEvents
// just to allow special configuration for these tests
//  we could configure in
// - application-test.yml for all tests
// - application-test-domain.yml especially for these tests
@ActiveProfiles({"test", "test-domain"})
// just to allow executing Maven running only these tests
// run "mvn surefire:test -Dgroups=domain-integration-test"
@Tag("integration-test")
@Tag("domain-integration-test")
public @interface BlogDomainTest {

  @ContextConfiguration
  class BlogDomainContext {
    // just to get an empty context with beans of this package
  }

  @TestConfiguration
  class BlogDomainTestConfiguration {

    @Bean
    BlogPostSink blogPostSink() {
      return mock(
        TestBlogPostSink.class,
        // reset the mock instructions and verifications between the tests
        withSettings(MockReset.AFTER) // reset instructions after test
          .useConstructor() // use default constructor
          .defaultAnswer(Mockito.CALLS_REAL_METHODS) // invoke real implementation, if not instructed
      );
    }

  }

  /**
   * Blog Post Sink for tests.
   * Provides an in-memory implementation and is resettable
   */
  class TestBlogPostSink implements BlogPostSink {

    private final Map<UUID, BlogPost> blogPosts = new HashMap<>();
    private final Map<UUID, BlogPost> initialBlogPosts = new HashMap<>();

    final void freezeInitialState() {
      this.initialBlogPosts.clear();
      this.initialBlogPosts.putAll(blogPosts);
    }

    final void restoreInitialState() {
      this.blogPosts.clear();
      this.blogPosts.putAll(initialBlogPosts);
    }

    @Override
    public long count() {
      return this.blogPosts.size();
    }

    @Override
    public void create(BlogPost post) {
      final var id = UUID.randomUUID();
      post.setId(id);
      this.blogPosts.put(id, post);

    }

    @Override
    public Stream<BlogPost> findAll() {
      return blogPosts.values().stream();
    }

    @Override
    public Optional<BlogPost> findById(UUID id) {
      return Optional.ofNullable(
        this.blogPosts.get(id)
      );
    }
  }

  class BlogPostSinkResetExtension implements BeforeEachCallback, AfterEachCallback {

    // freeze initial state (that was built during application startup)
    @Override
    public void beforeEach(ExtensionContext context) {
      SpringExtension
        .getApplicationContext(context)
        .getBeansOfType(BlogPostSink.class)
        .values()
        .stream()
        .filter(TestBlogPostSink.class::isInstance)
        .map(TestBlogPostSink.class::cast)
        .forEach(TestBlogPostSink::freezeInitialState);
    }

    // restore initial state after test
    @Override
    public void afterEach(ExtensionContext context) {
      SpringExtension
        .getApplicationContext(context)
        .getBeansOfType(BlogPostSink.class)
        .values()
        .stream()
        .filter(TestBlogPostSink.class::isInstance)
        .map(TestBlogPostSink.class::cast)
        .forEach(TestBlogPostSink::restoreInitialState);
    }

  }

}
