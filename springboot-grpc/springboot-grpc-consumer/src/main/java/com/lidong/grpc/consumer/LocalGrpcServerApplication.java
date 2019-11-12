package com.lidong.grpc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lidongl (lidong1665@163.com)
 * @since 2016/11/8
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LocalGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalGrpcServerApplication.class, args);
    }

}
