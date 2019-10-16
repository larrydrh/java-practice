package springrabbitmq.rabbitmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class BeanTest {
    @Bean
    public String string() {
        return "hello bean";
    }
}
