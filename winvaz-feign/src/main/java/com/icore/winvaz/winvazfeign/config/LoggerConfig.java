package com.icore.winvaz.winvazfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置类
 * @Author wdq
 * @Create 2021/4/27 20:15
 * @Version 1.0.0
 */
@Configuration
public class LoggerConfig {

    @Bean
    Logger.Level level() {
        return Logger.Level.FULL;
    }
}
