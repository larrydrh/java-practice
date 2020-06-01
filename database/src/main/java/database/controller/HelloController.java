package database.controller;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import database.Service.HelloCacheService;
import database.api.CommonResult;
import database.api.ResultCode;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private HelloCacheService helloCacheService;

//    @Timed(
//            value = "micrometer.hello.request",
//            histogram = true,
//            percentiles = {0.99,0.9,0.5},
//            extraTags = {"version", "1.0"}
//    )
    @GetMapping("/hello")
    public String hello(String a) throws Exception {
       log.warn("hello");
       return helloCacheService.helloCache();

    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> handleException(Exception e) {
        return CommonResult.failed(e.toString());
    }
}
