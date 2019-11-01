package demo;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author rhding
 */
public class JavaDemo {

    synchronized static public int add(int num1, int num2) {
        System.out.println("start add");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("return add");
        return num1 + num2;
    }
    public class ToGson {
        private int a = 1;
        private String b = "fads";
    }
    public void testGson() {
        ToGson tojson = new ToGson();
        Gson gson = new Gson();
        System.out.println(gson.toJson(tojson));
    }

    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        ll.add("ding");
        ll.add("ren");
        ll.pollLast();
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("one", "1");
        lhm.put("two", "2");

        JavaDemo javaDemo = new JavaDemo();
        javaDemo.testGson();
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        System.out.println(result1);
        System.out.println("hello world");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        System.out.println(formatter.format(System.currentTimeMillis()));
        threadPool.execute(() -> System.out.println(add(2, 4)));
        System.out.println(formatter.format(System.currentTimeMillis()));
        threadPool.execute(() -> System.out.println(add(1,3)));
        System.out.println(formatter.format(System.currentTimeMillis()));

    }
}
