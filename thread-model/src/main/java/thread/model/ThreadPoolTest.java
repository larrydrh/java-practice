package thread.model;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        log.info("",System.currentTimeMillis());
        scheduledExecutorService.schedule(()-> log.info("",System.currentTimeMillis()),1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
}
