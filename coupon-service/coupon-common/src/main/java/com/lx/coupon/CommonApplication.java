package com.lx.coupon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class CommonApplication {
    @Value("${test}")
    String info;
    @RequestMapping("/")
    public String getInfo(){
        return info + "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class,args);
    }
}
