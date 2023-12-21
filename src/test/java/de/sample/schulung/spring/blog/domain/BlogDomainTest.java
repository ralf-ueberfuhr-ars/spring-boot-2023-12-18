package de.sample.schulung.spring.blog.domain;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.annotation.*;

// some Java basic stuff
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
// only for test classes
@Target(ElementType.TYPE)
// Spring integration test - does NOT auto-configure bean validation etc.
@ExtendWith(SpringExtension.class)
// scan only classes in this package (and sub packages) to find annotated classes
@ComponentScan(basePackageClasses = BlogDomainTest.class)
// register beans
@Import({
  // enable validation
  ValidationAutoConfiguration.class,
  // register beans from the configuration (see below)
  BlogDomainTest.BlogDomainTestConfiguration.class
})
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

  @TestConfiguration
  class BlogDomainTestConfiguration {
    // we could register mocks here, that we might need for tests
  }

}
