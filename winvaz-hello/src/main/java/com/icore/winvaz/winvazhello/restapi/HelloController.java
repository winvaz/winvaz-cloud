package com.icore.winvaz.winvazhello.restapi;

import com.icore.winvaz.winvazhello.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Deciption Spring Cloud测试Demo
 * @Author wdq
 * @Create 2021/3/17 21:45
 * @Version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/winvaz-hello")
public class HelloController {

    @Autowired
    private Registration registration; // 服务注册

    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现

    /**
     * 改造成为注册服务提供者，向服务注册中心发布自己
     *
     * @param
     * @throws
     * @author wdq
     * @create 2021/3/19 22:18
     * @Return java.lang.String
     */
    @GetMapping("/index")
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

    @GetMapping("/hello-name")
    public String helloName(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello-object")
    public Book helloObject(@RequestHeader String name, @RequestHeader String author) {
        return new Book(name, author);
    }

    @PostMapping("/hello-string")
    public String helloToString(@RequestBody Book book) {
        return "Hello " + book.getName() + ", " + book.getAuthor();
    }


}