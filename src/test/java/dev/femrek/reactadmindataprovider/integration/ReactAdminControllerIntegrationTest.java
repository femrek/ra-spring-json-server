package dev.femrek.reactadmindataprovider.integration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = TestApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class ReactAdminControllerIntegrationTest {

    @Test
    void contextLoads() {
        // This test just ensures the Spring context loads successfully
        // The application will start and be ready for manual testing
    }
}
