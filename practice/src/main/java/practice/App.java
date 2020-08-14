package practice;


import java.util.Arrays;
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
        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("a = 1\nb=2");
        System.out.println(10/(3/1000));
        String a = "xxxx";
        for (int i = 0; i < a.length(); i++) {

        }
    }
}
