package com.icore.winvaz.winvazbus.service.impl;

import com.icore.winvaz.winvazbus.service.Consumer;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Author wdq
 * @Create 2021/6/4 17:02
 * @Version 1.0.0
 */
@Service
// 消费者通过@RabbitListener注解定义该类Hello队列的监听
@RabbitListener(queues = "Hello")
public class ConsumerImpl implements Consumer {

    // @RabbitHandler注解来指定对消费者的处理
    @RabbitHandler
    @Override
    public void receive(String message) {
        System.out.println("Receive : " + message);
    }
}
