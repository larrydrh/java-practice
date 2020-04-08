package practice.concurrent;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rhding
 */
public class ConcurrentMapPractice {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "2");

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1", "2");
    }
}
