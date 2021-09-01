package com.icore.winvaz.winvazeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 通过@EnableEurekaServer注解启动一个服务注册中心提供给其他应用进行对话。
@EnableEurekaServer
@SpringBootApplication
public class WinvazEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinvazEurekaApplication.class, args);
    }

}
