package com.lidong.grpc.produce;

import com.lidong.grpc.helloworld.GreeterGrpc;
import com.lidong.grpc.helloworld.HelloReply;
import com.lidong.grpc.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * 实现 定义一个实现服务接口的类
 *
 * @author Wangjinghao
 * @version v1.0.0
 * @date 2019/6/11
 **/
@Slf4j
@GrpcService
public class GreeterService extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage(("Hello " + req.getName())).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        log.info("Message from gRPC-Client:" + reply.getMessage());
    }
}

