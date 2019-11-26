package practice.concurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPool {
    public static void main(String[] args) throws FileNotFoundException {
        Executor threadPoolExecutor = Executors.newFixedThreadPool(2);
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        new FileInputStream(new File("test"));
        File a =  new File("xx");
    }
}
