package com.icore.winvaz.winvazhystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

// 启用Hystrix Dashboard功能
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
public class WinvazHystrixdashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinvazHystrixdashboardApplication.class, args);
    }

}
