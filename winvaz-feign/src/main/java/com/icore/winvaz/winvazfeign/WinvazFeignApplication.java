package com.icore.winvaz.winvazfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 开启Spring Cloud Feign的支持功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class WinvazFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinvazFeignApplication.class, args);
    }

}
