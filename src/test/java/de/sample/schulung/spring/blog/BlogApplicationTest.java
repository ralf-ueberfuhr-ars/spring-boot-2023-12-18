package de.sample.schulung.spring.blog;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

// some Java basic stuff
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
// only for test classes
@Target(ElementType.TYPE)
// Spring integration test without restrictions
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
// just to allow special configuration for these tests
//  we could configure in
// - application-test.yml for all tests
// - application-test-full.yml especially for these tests
@ActiveProfiles({"test", "test-full"})
// just to allow executing Maven running only these tests
// run "mvn surefire:test -Dgroups=full-integration-test"
@Tag("integration-test")
@Tag("full-integration-test")
public @interface BlogApplicationTest {

}
