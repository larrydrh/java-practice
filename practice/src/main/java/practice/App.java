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
    }
}
