package reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.security.InvalidParameterException;

public class FluxTest {


    @Test
    public void testFlattenMap() {
//        Flux.range(1, 10).buffer(5).map(le-> {})
        Flux.range(1, 10).buffer(5).log().flatMap(e-> Flux.fromIterable(e)).log().subscribe();
    }

    @Test
    public void testThrowError() {
        Flux.range(1,10).handle((e, sink)-> {
            if (e.equals(5)) {
                sink.error(new InvalidParameterException("param 5"));
            }
            if (e.equals(8)) {
                sink.error(new InvalidParameterException("param 8"));
            }
        }).log().onErrorResume(e ->{return Flux.range(20,10);}).log().subscribe();
    }


    @Test
    public void testOnErrorResume() {
        Flux.range(1, 10).map(e-> {if(e.equals(5)){
            return null;
        } else{ return e;}
        }).log().onErrorResume(e-> Flux.range(1,10)).log().subscribe();
    }
    @Test
    public void testOnErrorContinue() {
        Flux.range(1, 10).map(e-> {if(e.equals(5)){
            return null;
        } else{ return e;}
        }).log().onErrorResume(e-> Flux.range(1,10)).log().subscribe();
    }

    @Test
    public void testGroupBy() {
        Flux<String> flux = Flux.just("a1", "b1", "c1", "a2", "b2", "c2")
                .groupBy(s -> s.charAt(0))
                .concatMap(groupedFlux -> groupedFlux
                        .startWith("Group " + groupedFlux.key()));
        StepVerifier.create(flux)
                .expectNext("Group a", "a1", "a2")
                .expectNext("Group b", "b1", "b2")
                .expectNext("Group c", "c1", "c2")
                .verifyComplete();
    }
}
