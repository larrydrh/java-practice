package practice.spring;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


@SpringBootApplication
public class SpringBoot {
    ServletRequest servletRequest;
    ServletResponse servletResponse;
    @Override
    public String toString() {
        return "SpringBoot{}";
    }

    public SpringBoot() {
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBoot.class);
//        BeanFactory a =  new BeanFactory();

    }
}
