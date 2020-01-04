package reactor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ObserverTest {
    @Test
    public void testFlux() {
        Flux<String> f1 = Flux.just("ding", "ren");
        Flux<Integer> f2 = Flux.range(1,5);
        System.out.println(f1.blockFirst());
        assertEquals(f2.blockFirst(), 1);

       Flux.range(1, 5).repeat().subscribe(i -> System.out.println(i),
                error -> System.err.println("Error " + error),
                () -> System.out.println("Done"),
                sub -> sub.request(10));
    }

    @Test
    public void testVerifier() {
        StepVerifier.create(Flux.just(1, 2))
                .expectSubscription().expectNext(1).expectNext(2).expectComplete().verify();
    }

    @Test
    public void testOther() {
            Flux<Integer> ints = Flux.range(1, 4);
            ints.subscribe(i -> System.out.println(i),
                    error -> System.err.println("Error " + error),
                    () -> System.out.println("Done"),
                    sub -> sub.request(10));

            System.out.println(Flux.just("ding", "renhuan").filter(str-> str.equals("ding")).collectList());

    //        SynchronousSink;

            final Random random = new Random();
            Flux.generate(ArrayList::new, (list, sink) -> {
                int value = random.nextInt(100);
                list.add(value);
                sink.next(value);
                if (list.size() == 10) {
                    sink.complete();
                }
                return list;
            }).subscribe(System.out::println);
    //        Flux.interval(Duration.of(100, ChronoUnit.SECONDS)).subscribe(System.out::println);
            Flux.merge(new Publisher<String>() {
                @Override
                public void subscribe(Subscriber<? super String> s) {

                }
            });
            Flux.generate(sink -> {sink.next("hello"); sink.error(new Throwable("error"));}).subscribe(con -> {try {System.out.println(con);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            }, err -> {System.out.println(err.toString());});

            Flux.create(sink -> {
                sink.next(Thread.currentThread().getName());
                sink.complete();
            })
                    .publishOn(Schedulers.single())
                    .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                    .publishOn(Schedulers.elastic())
                    .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                    .subscribeOn(Schedulers.parallel())
                    .toStream()
                    .forEach(System.out::println);
    }
}
