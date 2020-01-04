package reactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
public class HelloWorldController
{
    Mono<String> stringMono;
    class Call extends Thread {

        public Call() {
        }
        public void run() {
            try {
                Thread.sleep(10);
                stringMono = Mono.just("hello zzzzzzzzzzzzz");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @GetMapping(value = "/hello/mono",
            produces = { "application/json" })
    public Mono<String> helloWorldMono() {
        Mono<String> result = Mono.empty();
        Call call =  new Call();
        call.start();

        return result.then(stringMono);
    }
    @GetMapping("/hello/flux")
    public Flux<Integer> helloWorldFlux() {
        return Flux.just(1, 2, 3);
    }
}
