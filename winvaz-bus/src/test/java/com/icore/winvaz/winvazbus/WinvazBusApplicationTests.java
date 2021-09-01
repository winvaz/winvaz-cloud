package com.icore.winvaz.winvazbus;

import com.icore.winvaz.winvazbus.service.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WinvazBusApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    void contextLoads() {
        producer.send();
    }

}
