package com.icore.winvaz.winvazribbon.restapi;

import com.icore.winvaz.winvazribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/3/20 19:13
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/winvaz-ribbon")
public class ConsumerController {

    /**
     * 创建RestTemplate来实现WINVAZ-HELLO服务提供的/hello/index接口进行调用。
     * @author wdq
     * @create 2021/3/20 19:16
     * @param null
     * @Return
     * @exception
     */
    /**
     * 开启断路器功能后，业务代码迁移到业务层
     */
    /*
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/helloConsumer")
    public String helloConsumer() {
        return restTemplate.getForEntity("http://WINVAZ-HELLO/hello/index",String.class).getBody();
    }
    */

    @Autowired
    private HelloService helloService;

    @GetMapping("/helloConsumer")
    public String helloConsumer() {
        return helloService.index();
    }

    @GetMapping("/index")
    public String index() throws InterruptedException {

        return "winvaz-ribbon Index !";
    }
}
