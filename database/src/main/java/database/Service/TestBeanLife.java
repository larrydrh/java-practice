package database.Service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestBeanLife implements BeanPostProcessor {


//    @Override
//    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization");
//        return null;
//    }
//
//    public Object postProcessBeforeInitialization(TestScope bean, String beanName) throws BeansException {
//        System.out.println("postProcessBeforeInitialization");
//        return bean;
//    }
//    public Object postProcessAfterInitialization(TestScope bean, String beanName) throws BeansException {
//        System.out.println("postProcessAfterInitialization");
//        return bean;
//    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("post construct");
    }

    @Bean(initMethod = "init")
    public void init() {
        System.out.println("init");
    }

}
