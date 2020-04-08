package practice.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class SynchronizeTest {
    public final static ArrayList<Vector> a = new ArrayList();
    public static void main(String[] args) throws InterruptedException {
          while (true) {
              a.add(new Vector(1000));
              Thread.sleep(100);
          }
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
//        Thread t1 = new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                System.out.println(list.size());
//                list.add("t1111");
//                System.out.println(list.size());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            synchronized (list) {
//                try {
//                    list.add("t2222");
//                    Thread.sleep(2000);
//                    list.add("t2333");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
    }
}
