package mybatis.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CommandService implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run on startup");
    }
}
