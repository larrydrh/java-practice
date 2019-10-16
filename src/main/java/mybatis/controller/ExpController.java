package mybatis.controller;

import mybatis.entity.HelloEntity;
import mybatis.service.HelloService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping(value = "/exp")
public class ExpController {
    @Autowired
    HelloService helloService;
    @Autowired
    RedissonClient redisson;
    @RequestMapping(value="getAllHello", method= RequestMethod.GET)
    public List<HelloEntity> getAllHello() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("mybatis.entity.HelloEntity");
//        HelloEntity entity = new HelloEntity();
        Method[] aa = clazz.getMethods();
        for (Method method:aa) {
            System.out.println("name: "+method.getName());
        }
        return helloService.getAllHello();
    }
    @RequestMapping(value="createHello", method= RequestMethod.POST)
    public HelloEntity createHello(@RequestBody HelloEntity helloEntity) {
        System.out.println(helloEntity.getBigValue());
        RMap<String, String> helloMap = redisson.getMap("hello");
        helloMap.put("BigValue", helloEntity.getBigValue());
        helloService.createHello(helloEntity);
        return helloEntity;
    }

}
