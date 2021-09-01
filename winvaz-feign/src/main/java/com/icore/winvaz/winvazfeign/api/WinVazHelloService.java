package com.icore.winvaz.winvazfeign.api;

import com.icore.winvaz.winvazfeign.config.LoggerConfig;
import com.icore.winvaz.winvazhelloapi.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/4/8 11:40
 * @Version 1.0.0
 */
// 需要在配置文中开启(true)关闭(false)Hystrix功能
// 通过@FeignClient注解的fallback属性来指定对应的服务降级实现类
// 这里的服务名不区分大小写，所以使用WINVAZ-HELLO和winvaz-hello都是可以的。属性名可以使用name或value
// 使用winvaz-hello-api时，服务绑定只能有一个
// @FeignClient(name = "WINVAZ-HELLO", configuration = LoggerConfig.class)
public interface WinVazHelloService {

    @GetMapping("/winvaz-hello/index")
    String index();

    @GetMapping("/winvaz-hello/hello-name")
    String helloName(@RequestParam("name") String name);

    @PostMapping("/winvaz-hello/hello-string")
    Book helloString(@RequestHeader("name") String name, @RequestHeader("author") String author);

    @GetMapping("/winvaz-hello/hello-object")
    String helloObject(@RequestBody Book book);
}
