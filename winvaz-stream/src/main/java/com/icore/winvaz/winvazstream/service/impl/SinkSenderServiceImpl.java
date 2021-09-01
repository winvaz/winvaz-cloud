package com.icore.winvaz.winvazstream.service.impl;

import com.icore.winvaz.winvazstream.service.SinkSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author wdq
 * @Create 2021/6/7 14:40
 * @Version 1.0.0
 */
@Slf4j
// 使用Spring Integration原生支持
@EnableBinding(value = {
    SinkSenderServiceImpl.SinkOutput.class
})
public class SinkSenderServiceImpl implements SinkSenderService {

    @Bean
    @InboundChannelAdapter(value = SinkOutput.OUTPUT, poller = @Poller(fixedDelay = "2000"))
    public MessageSource<Date> timeMessageSource() {
        return () -> new GenericMessage<>(new Date());
    }

    // 转换消息
    @Transformer(inputChannel = Sink.INPUT, outputChannel = Sink.INPUT)
    public Object transformer(Date messageDate) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(messageDate);
    }

    public interface SinkOutput {
        String OUTPUT = "input";

        @Output(SinkOutput.OUTPUT)
        MessageChannel output();
    }

    /*************************************************************/
    @Override
    public MessageChannel output() {
        return null;
    }
}
