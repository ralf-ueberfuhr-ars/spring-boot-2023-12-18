package de.sample.schulung.spring.blog.persistence;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

// some Java basic stuff
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
// only for test classes
@Target(ElementType.TYPE)
// only initialize datasource, repositories, ...
@DataJpaTest
// scan only classes in this package (and sub packages) to find annotated classes
@ComponentScan(basePackageClasses = BlogPersistenceTest.class)
// just to allow special configuration for these tests
//  we could configure in
// - application-test.yml for all tests
// - application-test-persistence.yml especially for these tests
@ActiveProfiles({"test", "test-persistence"})
// just to allow executing Maven running only these tests
// run "mvn surefire:test -Dgroups=persistence-integration-test"
@Tag("integration-test")
@Tag("persistence-integration-test")
public @interface BlogPersistenceTest {

}
