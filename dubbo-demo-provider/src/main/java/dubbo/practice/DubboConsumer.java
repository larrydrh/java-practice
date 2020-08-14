package dubbo.practice;

import dubbo.practice.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-demo-consumer.xml");
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService");
        demoService.test();
    }
}
