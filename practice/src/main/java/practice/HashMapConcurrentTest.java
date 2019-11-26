package practice;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author rhding
 */
public class HashMapConcurrentTest {
    public static void main(String[] args) throws Exception {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        ConcurrentHashMap cmap;
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =0; i< 100000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "tft" + i).start();
                }
            }
        }, "tft");
        a.start();
        a.join();
    }
}
