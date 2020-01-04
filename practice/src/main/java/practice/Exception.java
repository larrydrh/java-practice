package practice;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Exception extends Throwable {

    public static void main(String[] args) throws InterruptedException {
        Predicate<String> predicate = (s) -> s.length()>0;
        predicate.test("foo");

        Consumer<String> c = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        c.accept("renhuan");
        Consumer<String> cc = c.andThen(s -> System.out.println("ding"));
        cc.accept("renhuan");


        Supplier<String> n;
        n = ()-> "1";
        System.out.println(n.get());

        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.concat(" after apply function");
            }
        };
        function.apply("renhuan");
        Function<String, String> function1 = function.andThen(s->s.concat(" andThen"));
        function1.apply("ding");
    }
}

