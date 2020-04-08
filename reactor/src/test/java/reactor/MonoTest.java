package reactor;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;


public class MonoTest {
    @Test
    public  void testJust() throws InterruptedException {
        Mono<Long> clock = Mono.just(System.currentTimeMillis());
        System.out.println(clock.block());
        Thread.sleep(10_000);
        System.out.println(clock.block());

    }
    @Test
    public void testDefer() throws InterruptedException {
        Mono<Long> clock = Mono.defer(()-> Mono.just(System.currentTimeMillis()));
        System.out.println(clock.block());
        Thread.sleep(10_000);
        System.out.println(clock.block());
    }

    @Test
    public void testDelay() {
        StepVerifier.withVirtualTime(() -> Mono.delay(Duration.ofDays(5)))
                .thenAwait(Duration.ofDays(5))
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }


    @Test
    public void testWebClient() throws InterruptedException {
        Disposable disposable =WebClient.create("http://localhost:8080/hello/mono").get().retrieve().bodyToMono(String.class).subscribe(i -> System.out.println("next:" + i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
        Thread.sleep(500);
        disposable.dispose();
    }

    @Test
    public void testErrorHandle() {
//        Mono.fromFuture();
    }

    @Test
    public void testWebClientBatch() throws InterruptedException {
        Disposable disposable =WebClient.create("http://localhost:8080/hello/mono").get().retrieve().bodyToMono(String.class).subscribe(i -> System.out.println("next:" + i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(1));
        Disposable disposable2 =WebClient.create("http://localhost:8080/hello/mono").get().retrieve().bodyToMono(String.class).subscribe(i -> System.out.println("next:" + i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(1));
        Disposable disposable3 = WebClient.create("http://localhost:8080/hello/mono").get().retrieve().bodyToMono(String.class).subscribe(i -> System.out.println("next:" + i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(1));
        Thread.sleep(6000);
        disposable.dispose();
    }
}
