package springrabbitmq.rabbitmq;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class BeanTest {
    @Value("${local.server.address:0}")
    private String serverAddress;
    @Value("${server.port:0}")
    private String serverPort;
    @Autowired
    Environment environment;


    @Bean
    public String string() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "localhost-"+ RandomStringUtils.random(10, true, true);
        }
    }
    @Bean
    public String string1() {
        return "string1";
    }

    public void testServerAddress() throws Exception{
        System.out.println("server addr: " + InetAddress.getLocalHost().getHostAddress());
        System.out.println("server port: " + serverPort);
        System.out.println(environment.getProperty("server.port"));
    }
}
