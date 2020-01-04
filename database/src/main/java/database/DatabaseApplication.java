package database;

import database.Service.PrintService;
import database.Service.TestBeanDefinition;
import database.Service.TestScope;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatabaseApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DatabaseApplication.class);
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestBeanDefinition.class);
        beanDefinitionBuilder.addPropertyValue("name", "renhuan");
        beanFactory.registerBeanDefinition("testBeanDefinition",beanDefinitionBuilder.getBeanDefinition());

        TestBeanDefinition testBeanDefinition = (TestBeanDefinition) applicationContext.getBean("testBeanDefinition");

        TestScope testScope1 = applicationContext.getBean(TestScope.class);
        TestScope testScope2 = applicationContext.getBean(TestScope.class);
        System.out.println("testScope1:" + testScope1 + "testScope2:" + testScope2);

        testScope1.conditionalTestService.print();

        MeterRegistry meterRegistry = applicationContext.getBean(MeterRegistry.class);
        Counter counter = meterRegistry.counter("rhding__count");
        counter.increment();

    }
}
