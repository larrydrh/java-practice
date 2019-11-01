package springrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import springrabbitmq.rabbitmq.BeanTest;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class RabbitmqApplication {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(RabbitmqApplication.class, args);
        String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
        Object hello = context.getBean("string");
        System.out.println((String)hello);
        BeanTest test = context.getBean(BeanTest.class);
        test.testServerAddress();
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            ip.getHostName();

            System.out.println("Current hostname : " + ip.getHostName());
            System.out.println("Current IP address : " + ip.getHostAddress());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }

        System.out.println("hello");
    }
}
