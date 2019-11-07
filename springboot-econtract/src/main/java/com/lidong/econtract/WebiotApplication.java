package com.lidong.econtract;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lidong
 */
@Slf4j
@SpringBootApplication(scanBasePackages = "com.lidong.econtract")
public class WebiotApplication {


    public static void main(String[] args){
        SpringApplication.run(WebiotApplication.class, args);
    }

}
