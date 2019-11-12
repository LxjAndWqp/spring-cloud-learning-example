package com.lidong.grpc.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lidongl (lidong1665@163.com)
 * @since 2016/11/8
 */
@SpringBootApplication
public class LocalGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalGrpcServerApplication.class, args);
    }

}
