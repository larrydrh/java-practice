package demo;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;

public class JavaDemoTest {
    JavaDemo javaDemo = new JavaDemo();
    @Test
    public void helloTest() {
        Result result = JUnitCore.runClasses(ThreadDemo.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}