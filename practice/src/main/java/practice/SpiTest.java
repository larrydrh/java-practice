package practice;

import practice.spi.Robot;

import java.util.ServiceLoader;

public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        for (Robot robot : serviceLoader) {
            robot.test();
        }
    }
}
