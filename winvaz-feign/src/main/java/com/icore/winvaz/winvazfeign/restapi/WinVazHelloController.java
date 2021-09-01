package com.icore.winvaz.winvazfeign.restapi;

import com.icore.winvaz.winvazfeign.api.RefactorHelloService;
import com.icore.winvaz.winvazhelloapi.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wdq
 * @Create 2021/4/8 11:42
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/winvaz-feign")
public class WinVazHelloController {
    /*
    @Autowired
    private WinVazHelloService winVazHelloService;

    @GetMapping("/index")
    public String index() throws InterruptedException {
        return winVazHelloService.index();
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append(winVazHelloService.index()).append("\r\n");
        sb.append(winVazHelloService.helloName("西游记")).append("\r\n");
        sb.append(winVazHelloService.helloString("西游记", "吴承恩")).append("\r\n");
        sb.append(winVazHelloService.helloObject(new Book("三国演义", "罗贯中"))).append("\r\n");

        return sb.toString();
    }
    */

    // Feign继承特性重构
    @Autowired
    private RefactorHelloService refactorHelloService;

    @GetMapping("/index")
    public String index() throws InterruptedException {
        return refactorHelloService.index();
    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append(refactorHelloService.index()).append("\r\n");
        sb.append(refactorHelloService.helloName("西游记")).append("\r\n");
        sb.append(refactorHelloService.helloString("西游记", "吴承恩")).append("\r\n");
        sb.append(refactorHelloService.helloObject(new Book("三国演义", "罗贯中"))).append("\r\n");

        return sb.toString();
    }
}
