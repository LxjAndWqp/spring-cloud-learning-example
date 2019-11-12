package com.lidong.grpc.produce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lidongl (lidong1665@163.com)
 * @since 2016/11/8
 */
//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class CloudGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGrpcServerApplication.class, args);
    }

}
