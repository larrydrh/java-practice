package springrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RabbitmqApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(RabbitmqApplication.class, args);
        String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
        Object hello = context.getBean("string");
        System.out.println("hello");
    }
}
