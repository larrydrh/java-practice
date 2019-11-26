package practice;


import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.BooleanUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author rhding
 */
public class App {
    Integer a = 0;
    String b = "1";
    public static void testStatic() {
        System.out.println("in static");
    }
    public static void main(String[] args) throws Exception{
        System.out.println(System.currentTimeMillis());
        App app = new App();
        String[] a = {"1", "2"};
        System.out.println(a);

//        app.wait(1000);
        System.out.println(System.currentTimeMillis());
    }
}
