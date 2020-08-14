package dubbo.practice;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.CountDownLatch;

public class DubboProvider {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-demo-provider.xml");
        context.start();
        System.out.println("dubbo provider started");
        new CountDownLatch(1).await();

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                // TODO Auto-generated method stub
                return o1 - o2;
            }
        });
    }
}
