package database.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.IOException;

@Service
public class PrintService {
    @Value("${test:test_value_construct}")
    private String t;
    StopWatch stopWatch = new StopWatch();

    @Value("classpath:application.yml")
    Resource resource;

    PrintService(){
        System.out.println("t" + t);
        stopWatch.start();
    }


    public void print() throws IOException {

        System.out.println("in print");
    }

    @Scheduled(fixedDelay = 1000 , initialDelay = 1000)
    public void schedule() throws InterruptedException, IOException {
        stopWatch.stop();

        resource.getFile();
        System.out.println(stopWatch.getLastTaskTimeMillis());
        stopWatch.start();
        System.out.println("@Scheduled(initialDelay = 1000)");
        Thread.sleep(10000);
    }
}
