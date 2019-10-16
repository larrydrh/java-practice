package rabbitmq;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Exp {
    public static void main(String[] args) throws Exception{
        List<String> a = new ArrayList<>(asList("1asf", "2"));
        Stream<String> language = Stream.of("java", "python", "C++","php","java");
        List<String> listResult = language.collect(Collectors.toList());
        listResult.forEach(System.out::println);
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("ren", 1);
        hashMap.remove("ren");
        Thread.sleep(10);
    }
}
