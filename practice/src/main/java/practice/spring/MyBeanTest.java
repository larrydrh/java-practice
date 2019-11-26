package practice.spring;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Comparator;
import java.util.EventListener;
import java.util.Observable;

public class MyBeanTest {
    public String a = "hello";
    Observable observable;
    EventListener eventListener;
    Comparator comparator;
    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("bean.xml");

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(resource);
        MyBeanTest myBeanTest = factory.getBean(MyBeanTest.class);
        System.out.println(myBeanTest.a);
    }
}
