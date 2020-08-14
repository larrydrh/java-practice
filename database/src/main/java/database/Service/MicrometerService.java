package database.Service;

import database.DatabaseApplication;
import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

@Service
public class MicrometerService implements CommandLineRunner {

    @Autowired
    private MeterRegistry meterRegistry;

    private void summaryTest() {
        DistributionSummary summary = DistributionSummary.builder("rhding_summary")
                .description("a description of what this summary does") // optional
                .publishPercentileHistogram()
                .minimumExpectedValue(1L)
                .maximumExpectedValue(48L)
                .register(meterRegistry);

        summary.record(10);
        summary.record(20);
    }

    public void test() {
        Counter counter = meterRegistry.counter("rhding__count");
        counter.increment();
        counter.increment();




        AtomicInteger number  = meterRegistry.gauge("number", new AtomicInteger());
        number.set(0);
        new Thread(()-> {
            try {
                number.getAndAdd(20);
                Thread.sleep(1000);
                System.out.println(number.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Timer timer = Timer
                .builder("rhding.timer")
                .description("a description of what this timer does") // optional
                .tags("region", "test") // optional
                .register(meterRegistry);

        timer.record(1111, TimeUnit.MILLISECONDS);

        Gauge gauge = Gauge
                .builder("rhding_gauge", DatabaseApplication::getCount)
                .register(meterRegistry);
        timer.record(11111, TimeUnit.MILLISECONDS);
    }

    public void timerTest() throws InterruptedException {
        Timer.Sample sample = Timer.start(meterRegistry);
        Thread.sleep(1000);
        sample.stop(meterRegistry.timer("rhding.timer1", "ragnarosCost", "success"));
    }

    @Override
    public void run(String... args) throws Exception {
        MultiGauge multiGauge = MultiGauge.builder("rhding_multigauge")
                .tag("tagkey", "tagvalue")
                .register(meterRegistry);
        Iterable<MultiGauge.Row<?>> gauges = Arrays.asList(MultiGauge.Row.of(Tags.of("rhding", "value1"), 123), MultiGauge.Row.of(Tags.of("rhding", "value2"), 123));
        List<MultiGauge.Row<?>> gaugesRef = (List<MultiGauge.Row<?>>)gauges;
        multiGauge.register(gauges);
        //        Timer timer = Timer
//                .builder("rhding.timer")
//                .description("a description of what this timer does") // optional
//                .tags("region", "test") // optional
//                .sla()
//                .register(meterRegistry);
//        Supplier supplier =  timer.wrap((Supplier<Integer>) ()-> {
//            try {
//                System.out.println("in supplier");
//                Thread.sleep(1998);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return 1;
//        });
//        supplier.get();
//        supplier.get();
    }
}
