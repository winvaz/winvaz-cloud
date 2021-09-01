package com.icore.winvaz.winvazhelloapi.api;

import com.icore.winvaz.winvazhelloapi.model.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/4/8 18:34
 * @Version 1.0.0
 */
@RequestMapping("/refactor")
public interface WinVazHelloService {

    @GetMapping("/index")
    String index() throws InterruptedException;

    @GetMapping("/hello-name")
    String helloName(@RequestParam("name") String name);

    @PostMapping("/hello-object")
    String helloObject(@RequestBody Book book);

    @GetMapping("/hello-string")
    Book helloString(@RequestHeader("name") String name, @RequestHeader("author") String author);

}