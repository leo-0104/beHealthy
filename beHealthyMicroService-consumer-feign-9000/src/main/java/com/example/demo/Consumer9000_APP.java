package com.example.demo;

import com.example.demo.service.SendService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableEurekaClient   //eureka客户端，用于从eureka服务端上发现服务
@EnableFeignClients  //负载均衡采用feign
@EnableBinding(SendService.class)
public class Consumer9000_APP {
    public static void main(String[] args) {
        SpringApplication.run(Consumer9000_APP.class, args);
    }
}
