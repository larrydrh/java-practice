package reactor;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.*;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestProcessor {
    @Test
    public void testDirectProcessor(){
        DirectProcessor<Integer> directProcessor = DirectProcessor.create();
        Flux<Integer> flux = directProcessor
                .filter(e -> e % 2 == 0)
                .map(e -> e +1);
        Mono<Integer> mono1 = Mono.from(directProcessor.filter(e-> e.equals(19)));
        mono1.doOnNext(e -> {
            System.out.println("mono1 onNext:{}"+e);
        }).subscribe(e -> {
            System.out.println("mono1 subscriber:{}"+e);
        });
        flux.subscribe(new Subscriber<Integer>() {
            private Subscription s;
            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(100);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("subscribe:{}"+ integer);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage() + t);
            }

            @Override
            public void onComplete() {

            }
        });

        IntStream.range(1,20)
                .forEach(e -> {
                    directProcessor.onNext(e);
                });

        directProcessor.onComplete();
        directProcessor.blockLast();
    }

    @Test
    public void testEmitterProcessor() throws InterruptedException {
        int bufferSize = 3; //小于8的会被重置为8
        FluxProcessor<Integer, Integer> processor = EmitterProcessor.create(bufferSize);
        Flux<Integer> flux1 = processor.map(e -> e);
        Flux<Integer> flux2 = processor.map(e -> e*10);

        IntStream.rangeClosed(1,8).forEach(e -> {
            System.out.println("emit:{}"+e);
            processor.onNext(e); //如果发布的未消费数据超过bufferSize,则会阻塞在这里
        });

        flux1.subscribe(e -> {
            System.out.println("flux1 subscriber:{}"+e);
        });

        IntStream.rangeClosed(9,10).forEach(e -> {
            System.out.println("emit:{}"+e);
            processor.onNext(e);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });

        //这个是后面添加的订阅,只能消费之后发布的数据
        flux2.subscribe(e -> {
            System.out.println("flux2 subscriber:{}"+ e);
        });

        processor.onNext(11);
        processor.onNext(12);

        processor.onComplete();
        processor.blockLast();
    }

    @Test
    public void testReplayProcessor() throws InterruptedException {
        ReplayProcessor<Integer> replayProcessor = ReplayProcessor.create(3);
        Flux<Integer> flux1 = replayProcessor
                .map(e -> e);
        Flux<Integer> flux2 = replayProcessor
                .map(e -> e);

        flux1.subscribe(e -> {
            System.out.println("flux1 subscriber:{}"+e);
        });


        IntStream.rangeClosed(1,5)
                .forEach(e -> {
                    replayProcessor.onNext(e);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                });

        System.out.println("finish publish data");
        TimeUnit.SECONDS.sleep(3);

        System.out.println("begin to subscribe flux2");
        flux2.subscribe(e -> {
            System.out.println("flux2 subscriber:{}"+e);
        });

        replayProcessor.onComplete();
        replayProcessor.blockLast();
    }

    @Test
    public void testTopicProcessor() throws InterruptedException {
        TopicProcessor<Integer> topicProcessor = TopicProcessor.<Integer>builder()
                .share(true)
//                .executor(Executors.newSingleThreadExecutor())
                .build();

        Flux<Integer> flux1 = topicProcessor
                .map(e -> e);
        Flux<Integer> flux2 = topicProcessor
                .map(e -> e);
        Flux<Integer> flux3 = topicProcessor
                .map(e -> e);

        AtomicInteger count = new AtomicInteger(0);
        flux1.filter(e-> e.equals(66)).subscribe(e -> {
            System.out.println("flux1 subscriber:{}"+e);
            count.incrementAndGet();
        });
        flux2.filter(e-> e.equals(67)).subscribe(e -> {
            System.out.println("flux2 subscriber:{}"+e);
        });
        flux3.filter(e->e.equals(68)).subscribe(e -> {
            System.out.println("flux3 subscriber:{}"+e);
        });
        Mono<Integer> mono1 = Mono.from(topicProcessor.filter(e-> e.equals(69)));
        mono1.subscribe(e -> {
            System.out.println("mono1 subscriber:{}"+e);
        });

        IntStream.rangeClosed(1,100)
                .parallel()
                .forEach(e -> {
//                    LOGGER.info("emit:{}",e);
                    topicProcessor.onNext(e);
                });

        topicProcessor.onComplete();
        topicProcessor.blockLast();

        TimeUnit.SECONDS.sleep(10);
        System.out.println(count.get());
    }


}
