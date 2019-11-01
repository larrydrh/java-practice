package kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {
    public static ConfigurableApplicationContext ctx;
    public static void displayAllBeans() {
        String[] allBeanNames = ctx.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
    public static void main(String[] args) {
         ctx = SpringApplication.run(KafkaApplication.class, args);

    }
}
