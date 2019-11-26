package practice.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeController {

    @RequestMapping(value = "/parctice")
    public String parctice() {
        System.out.println("parctice");
        return "parctice";
    }
}
