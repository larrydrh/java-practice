package mybatis.service;

import mybatis.entity.HelloEntity;
import mybatis.mapper.HelloMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {
    private final HelloMapper helloMapper;
    public HelloService(HelloMapper helloMapper) {
        this.helloMapper = helloMapper;
    }
    public List<HelloEntity> getAllHello() {
        return this.helloMapper.getAllHello();
    }
    public int createHello(HelloEntity helloEntity) {
        return this.helloMapper.createHello(helloEntity);
    }
}
