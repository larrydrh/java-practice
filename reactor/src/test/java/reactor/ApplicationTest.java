package reactor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.controller.HelloWorldController;
import reactor.test.StepVerifier;

@SpringBootTest
public class ApplicationTest {
    @Test
    public void testWebTestClient() {
        HelloWorldController helloWorldController = new HelloWorldController();
        WebTestClient
                .bindToController(helloWorldController)
                .build()
                .get()
                .uri("/hello/mono")
                .exchange()
//                .expectHeader().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                .expectStatus().is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody()
                .as(StepVerifier::create)
//                .expectNextCount(1)
                .expectNext("hello world")
                .expectComplete()
                .verify();

    }

}
