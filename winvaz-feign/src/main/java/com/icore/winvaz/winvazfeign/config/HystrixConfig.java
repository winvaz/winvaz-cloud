package com.icore.winvaz.winvazfeign.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 关闭Hystrix配置类
 * @Author wdq
 * @Create 2021/4/16 19:53
 * @Version 1.0.0
 */
// @Configuration
public class HystrixConfig {

    // @Bean
    // @Scope("prototype")
    Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
