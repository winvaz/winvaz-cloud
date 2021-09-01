package com.icore.winvaz.winvazhello.restapi;

import com.icore.winvaz.winvazhelloapi.model.Book;
import com.icore.winvaz.winvazhelloapi.api.WinVazHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author wdq
 * @Create 2021/4/8 18:39
 * @Version 1.0.0
 */
@Slf4j
@RestController
public class RefactorHelloController implements WinVazHelloService {

    @Autowired
    private Registration registration; // 服务注册

    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现

    @Override
    public String index() throws InterruptedException {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances(registration.getServiceId());

        // 测试超时
        // 模拟服务阻塞(长时间未响应)
        // 让线程等待几秒钟
        /*int i = new Random().nextInt(3000);
        log.info("sleepTime：{}", i);
        Thread.sleep(i);*/

        serviceInstanceList.forEach(serviceInstance -> {
            log.info("/index，host:{}，service_id:{}", serviceInstance.getHost(), serviceInstance.getServiceId());
        });
        return "winvaz-hello Index !";
    }

    @Override
    public String helloName(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @Override
    public Book helloString(@RequestHeader("name") String name, @RequestHeader("author") String author) {
        return new Book(name, author);
    }

    @Override
    public String helloObject(@RequestBody Book book) {
        return "Hello " + book.getName() + ", " + book.getAuthor();
    }
}
