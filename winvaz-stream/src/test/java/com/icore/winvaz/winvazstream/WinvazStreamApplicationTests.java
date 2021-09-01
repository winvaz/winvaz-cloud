package com.icore.winvaz.winvazstream;

import com.icore.winvaz.winvazstream.service.SinkSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
class WinvazStreamApplicationTests {

    // 注入绑定接口
    @Autowired
    private MessageChannel input;
    // private SinkSenderService sinkSenderService;

    @Test
    void contextLoads() {
        // sinkSenderService.output().send(MessageBuilder.withPayload("From SinkSender").build());
        input.send(MessageBuilder.withPayload("From SinkSender").build());
    }

}
