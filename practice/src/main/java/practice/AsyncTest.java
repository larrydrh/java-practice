package practice;

import org.aspectj.apache.bcel.classfile.InnerClass;

import java.util.HashMap;
import java.util.function.Consumer;

public class AsyncTest
{
    public static class InnerClass {
        Consumer<String> consumer;
        InnerClass(Consumer consumer) {
            this.consumer = consumer;
        }
        public void asyncsend(){
            consumer.accept("xxxxx");
        }
    }
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass(e->{
            System.out.println("get info " + e);
        });

        innerClass.asyncsend();
        HashMap hashMap = new HashMap();

    }
}
