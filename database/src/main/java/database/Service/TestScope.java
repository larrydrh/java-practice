package database.Service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(scopeName = "prototype")
public class TestScope implements CommandLineRunner, BeanPostProcessor {
    @Autowired
    public ConditionalTestService conditionalTestService;

//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization");
//        return bean;
//    }
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization");
//        return bean;
//    }
//
//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("post construct");
//    }
//
//    @Bean(initMethod = "init")
//    public void init() {
//        System.out.println("init");
//    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run command");
    }
}
