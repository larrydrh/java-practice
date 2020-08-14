package concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static class DemoTask extends RecursiveTask<Integer> {
        @Override
        protected Integer compute() {
            System.out.println("helo");
            return 1;
        }
    }
    public static void main(String[] args) throws InterruptedException {
//        ForkJoinPool pool = new ForkJoinPool();
//        DemoTask demoTask = new DemoTask();
//        pool.submit(demoTask);
//        pool.awaitTermination(1, TimeUnit.MINUTES);
        InputStream in = getClass().getResourceAsStream("/test");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        System.out.println(reader.read());
    }
}
