package com.icore.winvaz.winvazbus.config;

import com.icore.winvaz.winvazcommon.config.Config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wdq
 * @Create 2021/6/4 17:07
 * @Version 1.0.0
 */
@Configuration
public class RabbitConfig implements Config {

    /**
     * 创建一个队列
     * @author wdq
     * @create 2021/6/4 17:09
     * @param
     * @Return java.util.Queue
     * @exception
     */
    @Bean
    Queue queue() {
        return new Queue("Hello");
    }
}
