package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient   //eureka客户端，用于从eureka服务端上发现服务
@EnableFeignClients  //负载均衡采用feign
public class Consumer9000_APP {
    public static void main(String[] args) {
        SpringApplication.run(Consumer9000_APP.class, args);
    }
}
