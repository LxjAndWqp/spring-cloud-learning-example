package com.lidong.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lidongl (lidong1665@163.com)
 */
//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class CloudGrpcServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGrpcServerApplication.class, args);
    }


    //实例化 RestTemplate 实例
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
