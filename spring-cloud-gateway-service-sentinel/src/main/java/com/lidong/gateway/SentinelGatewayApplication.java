package com.lidong.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 开启服务发现
 */
@EnableDiscoveryClient
@SpringBootApplication
public class SentinelGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelGatewayApplication.class, args);
    }

}

