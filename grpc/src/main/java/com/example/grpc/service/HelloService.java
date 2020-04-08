package com.example.grpc.service;

import com.example.grpc.GreeterGrpc;
import com.example.grpc.HelloWorld;
import io.grpc.services.ChannelzService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.context.annotation.Bean;

/**
 * @author rhding
 */
@GRpcService
public class HelloService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloWorld.HelloRequest request, StreamObserver<HelloWorld.HelloReply> responseObserver) {
        HelloWorld.HelloReply helloReply = HelloWorld.HelloReply.newBuilder()
                .setMessage(request.getMessage() + "hello")
                .build();
        responseObserver.onNext(helloReply);
        responseObserver.onCompleted();
    }

    @Bean
    public ChannelzService getChannelzService() {
        return ChannelzService.newInstance(10);
    }
}