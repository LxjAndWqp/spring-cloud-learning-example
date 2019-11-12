package com.lidong.grpc;


import com.lidong.grpc.helloworld.GreeterGrpc;
import com.lidong.grpc.helloworld.HelloReply;
import com.lidong.grpc.helloworld.HelloRequest;
import io.grpc.Channel;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcClientService {

    @GrpcClient("cloud-grpc-server")
    private GreeterGrpc.GreeterBlockingStub greeterBlockingStub;

    public String sendMessage(final String name) {
        try {
            final HelloReply response = this.greeterBlockingStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode();
        }
    }
}
