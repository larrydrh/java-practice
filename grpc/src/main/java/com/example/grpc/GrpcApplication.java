package com.example.grpc;

import io.grpc.ServerBuilder;
import io.grpc.services.ChannelzService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

/**
 * @author rhding
 */
@SpringBootApplication
public class GrpcApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(GrpcApplication.class, args);
//        ;
//        ServerBuilder.forPort(50052).addService(ChannelzService.newInstance(1))
//                .build()
//                .start();

    }

}
