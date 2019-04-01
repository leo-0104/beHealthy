package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy   //开启路由网关
public class ZuulGateWay9527_APP {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGateWay9527_APP.class,args);
    }
}
