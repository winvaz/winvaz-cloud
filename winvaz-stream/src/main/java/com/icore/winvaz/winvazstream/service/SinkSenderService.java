package com.icore.winvaz.winvazstream.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * 注入绑定接口
 * @Author wdq
 * @Create 2021/6/7 14:15
 * @Version 1.0.0
 */
public interface SinkSenderService {

    @Output(Sink.INPUT)
    MessageChannel output();
}
