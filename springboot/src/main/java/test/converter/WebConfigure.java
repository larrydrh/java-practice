package test.converter;

import org.springframework.cglib.core.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Set;

public class WebConfigure implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
//        Set
    }
}
