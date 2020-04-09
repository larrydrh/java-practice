package database.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yitu.speech.*.mapper")
public class MybatisPlusConfig {
}