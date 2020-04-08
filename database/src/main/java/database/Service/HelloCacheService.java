package database.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HelloCacheService {
    @Cacheable("helloCache")
    public String helloCache() {
        System.out.println("enter to helloCache");
        return "hello";
    }
}
