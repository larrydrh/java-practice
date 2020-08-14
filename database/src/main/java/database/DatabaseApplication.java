package database;

import database.Service.PrintService;
import database.Service.TestBeanDefinition;
import database.Service.TestScope;
import io.micrometer.core.instrument.*;
import jdk.javadoc.internal.tool.DocEnvImpl;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StopWatch;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
//@EnableScheduling
@EnableCaching
public class DatabaseApplication {
    public static Integer count = 0;

    public static void main(String[] args) throws InterruptedException {


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

    }

    public static Integer getCount() {
        return count;
    }
}
