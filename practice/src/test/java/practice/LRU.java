package practice;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRU {

    LinkedHashMap<String, String> cache = new LinkedHashMap<String, String>(16, 0.75f, true);


    @Test
    public void testLRU() {
        for (int i = 0; i < 20; i++) {
            cache.put("key"+i, "value" + i);
        }
        cache.get("key1");
        System.out.println(cache.toString());
    }
}
