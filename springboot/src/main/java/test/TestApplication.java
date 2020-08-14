package test;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.availability.ApplicationAvailability;

import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        BeanDefinition beanDefinition;
        ApplicationAvailability applicationAvailability;
        ConcurrentHashMap cx;

    }

}
