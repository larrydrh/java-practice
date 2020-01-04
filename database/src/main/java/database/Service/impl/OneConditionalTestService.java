package database.Service.impl;

import database.Service.ConditionalTestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@ConditionalOnProperty(
        value="condition",
        havingValue = "one",
        matchIfMissing = true)
@Service
public class OneConditionalTestService implements ConditionalTestService {
    @Override
    public void print() {
        System.out.print("one");
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
