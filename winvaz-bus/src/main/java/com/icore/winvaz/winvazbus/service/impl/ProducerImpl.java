package com.icore.winvaz.winvazbus.service.impl;

import com.icore.winvaz.winvazbus.service.Producer;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author wdq
 * @Create 2021/6/4 16:51
 * @Version 1.0.0
 */
@Service
public class ProducerImpl implements Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void send() {
        String context = "Hello " + new Date();
        System.out.println("Send : " + context);
        amqpTemplate.convertAndSend(context);
    }
}