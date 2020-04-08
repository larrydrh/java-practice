package database.controller;

import database.Service.HelloCacheService;
import database.api.CommonResult;
import database.api.ResultCode;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloCacheService helloCacheService;

    @Timed(
            value = "micrometer.hello.request",
            histogram = true,
            percentiles = {0.99,0.9,0.5},
            extraTags = {"version", "1.0"}
    )
    @GetMapping("/hello")
    public String hello(String a) throws Exception {
       return helloCacheService.helloCache();

    }

    @ExceptionHandler(Exception.class)
    public CommonResult<String> handleException(Exception e) {
        return CommonResult.failed(e.toString());
    }
}
