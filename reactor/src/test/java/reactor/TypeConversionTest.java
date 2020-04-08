package reactor;

import org.junit.jupiter.api.Test;
import org.springframework.core.ReactiveAdapter;
import org.springframework.core.ReactiveAdapterRegistry;
import reactor.core.publisher.Flux;

public class TypeConversionTest {

    @Test
    public void test() {
//        ReactiveAdapter reactiveAdapter = new ReactiveAdapter();

        ReactiveAdapterRegistry reactiveAdapterRegistry = new ReactiveAdapterRegistry();
        ReactiveAdapter reactiveAdapter = reactiveAdapterRegistry.getAdapter(Flux.class);

    }
}
