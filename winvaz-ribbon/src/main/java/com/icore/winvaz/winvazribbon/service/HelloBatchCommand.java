package com.icore.winvaz.winvazribbon.service;

import com.icore.winvaz.winvazribbon.model.Person;
import com.netflix.hystrix.HystrixCommand;

import java.util.List;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.asKey;

/**
 * @Deciption 为请求合并的实现准备一个批量请求命令的实现
 * @Author wdq
 * @Create 2021/4/1 17:08
 * @Version 1.0.0
 */
public class HelloBatchCommand extends HystrixCommand<List<Person>> {

    HelloService helloService;
    List<Long> personIds;

    public HelloBatchCommand(HelloService helloService, List<Long> personIds) {
        super(Setter.withGroupKey(asKey("helloServiceCommand")));
        this.helloService = helloService;
        this.personIds = personIds;
    }

    @Override
    protected List<Person> run() throws Exception {
        return helloService.findAll(personIds);
    }
}
