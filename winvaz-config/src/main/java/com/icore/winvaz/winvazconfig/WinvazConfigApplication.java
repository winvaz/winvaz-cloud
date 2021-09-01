package com.icore.winvaz.winvazconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

// 通过@EnableConfigServer注解开启config的服务端功能
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class WinvazConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinvazConfigApplication.class, args);
    }

}
