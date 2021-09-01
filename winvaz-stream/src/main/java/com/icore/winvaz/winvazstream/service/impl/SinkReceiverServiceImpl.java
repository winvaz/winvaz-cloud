package com.icore.winvaz.winvazstream.service.impl;

import com.icore.winvaz.winvazstream.service.SinkReceiverService;
import com.icore.winvaz.winvazstream.service.SinkSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

/**
 * @Author wdq
 * @Create 2021/6/7 11:47
 * @Version 1.0.0
 */
@Slf4j
@Service
// 注入绑定接口
// @EnableBinding(value = {Sink.class, SinkSenderService.class})
// 使用Spring Integration原生支持
@EnableBinding(value = Sink.class)
public class SinkReceiverServiceImpl implements SinkReceiverService {

    // 使用Spring Integration原生支持
    @ServiceActivator(inputChannel = Sink.INPUT)
    @Override
    public void receive(Object payload) {
        log.info("Received: {}", payload);
    }

    /*
    @StreamListener(Sink.INPUT)
    @Override
    public void receive(Object payload) {
        log.info("Received: {}", payload);
    }
    */
}
