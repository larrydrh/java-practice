package com.example.grpc.service;

import com.example.grpc.GrpcApplication;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "grpc.enableReflection=true",
                "grpc.port=0",
                "grpc.shutdownGrace=-1"
        })
public class HelloServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Rule
    public OutputCaptureRule outputCaptureRule;

    @Test
    public void disabledServerTest() throws Throwable {

    }

}