package grpcclient;

import com.example.grpc.GreeterGrpc;
import com.example.grpc.HelloWorld;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.ExecutionException;

public class GrpcClientApplication {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("10.10.25.89", 50052).usePlaintext().build();
        GreeterGrpc.GreeterFutureStub stub = GreeterGrpc.newFutureStub(channel);
        HelloWorld.HelloReply reply = stub.sayHello(HelloWorld.HelloRequest.newBuilder().setMessage("hjello").build()).get();
        System.out.println(reply);
    }
}
