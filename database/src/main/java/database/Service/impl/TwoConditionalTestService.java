package database.Service.impl;

import database.Service.ConditionalTestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
        value="condition",
        havingValue = "two")
@Service
public class TwoConditionalTestService implements ConditionalTestService {
    @Override
    public void print() {
        System.out.println("two");
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("two");
            Thread.sleep(1000);
        }
    }
}
