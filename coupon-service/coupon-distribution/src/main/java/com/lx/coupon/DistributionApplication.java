package com.lx.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableJpaAuditing  //审计
@EnableFeignClients
public class DistributionApplication {
    @Bean
    @LoadBalanced //ribbon 负载均衡
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {

        SpringApplication.run(DistributionApplication.class,args);
    }
}
