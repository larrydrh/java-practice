package reactor.service;

import org.reactivestreams.Subscription;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Service;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.extra.processor.TopicProcessor;

import java.time.Duration;
import java.util.List;

@Service
public class HelloService {
    TopicProcessor<String> processor;
    Flux<String> hotFlux;
    Mono<String> result;

    public HelloService() {
        processor = TopicProcessor.<String>builder()
                .share(true)
//                .executor(Executors.newSingleThreadExecutor())
                .build();
        hotFlux = processor
                .publish()
                .autoConnect()
                .onBackpressureBuffer(10).bufferTimeout(5, Duration.ofSeconds(5)).log().flatMap(e-> Flux.fromIterable(e)).cache(Duration.ofSeconds(20));
    }

    public Mono<String> hello(String request) {
        processor.onNext(request+ 1);
        processor.onNext(request);
        return Mono.from(hotFlux.filter(e -> e.equals(request)).log());
//        return Mono.empty();
    }
}
