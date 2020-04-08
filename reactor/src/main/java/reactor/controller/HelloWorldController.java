package reactor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.service.HelloService;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloWorldController
{
    AtomicInteger requestCount = new AtomicInteger(0);
    @Autowired
    HelloService helloService;
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
        String request = "hello" + requestCount.getAndAdd(1);
        Mono<String> result = helloService.hello(request);
        result.doOnNext(e-> System.out.println(request + "  " + e));
        result.then();
        return result;
    }

    @GetMapping("/hello/flux")
    public Flux<Integer> helloWorldFlux() {
        return Flux.just(1, 2, 3);
    }
}
