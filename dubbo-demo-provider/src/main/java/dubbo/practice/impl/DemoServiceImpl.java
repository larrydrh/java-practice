package dubbo.practice.impl;

import dubbo.practice.api.DemoService;

public class DemoServiceImpl implements DemoService {

    @Override
    public void test() {
        System.out.println("test ....");
    }
}
