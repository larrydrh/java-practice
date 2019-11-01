package demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class ContainerDemo {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<String, String>(10);
        hashMap.put("hello", null);
        String b = hashMap.get("hello");
        String c = hashMap.get("null");
        boolean x = hashMap.containsKey("null");

        Integer i = 0;
//        checkArgument(i >0, "Argument was %s but expected nonnegative", i);
        if (i.equals(null)) {

        }
    }
}
