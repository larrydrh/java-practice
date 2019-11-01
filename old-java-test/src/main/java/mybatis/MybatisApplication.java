package mybatis;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MybatisApplication {
    public static ConfigurableApplicationContext ctx;
    public static void displayAllBeans() {
        String[] allBeanNames = ctx.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
    public static void main(String[] args) {
         ctx = SpringApplication.run(MybatisApplication.class, args);
         displayAllBeans();
    }
}
