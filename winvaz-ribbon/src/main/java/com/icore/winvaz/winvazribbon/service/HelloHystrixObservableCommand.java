package com.icore.winvaz.winvazribbon.service;

import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/3/31 11:43
 * @Version 1.0.0
 */
public class HelloHystrixObservableCommand extends HystrixObservableCommand {

    private RestTemplate restTemplate;
    private Long id;

    public HelloHystrixObservableCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected Observable construct() {
        return Observable.unsafeCreate((Observable.OnSubscribe<String>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    String object = restTemplate.getForObject("http://WINVAZ-HELLO/hello/index/id/{1}",
                            String.class, id);
                    subscriber.onNext(object);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    /**
     * 定义服务降级
     * @author wdq
     * @create 2021/3/31 13:56
     * @param
     * @Return rx.Observable
     * @exception
     */
    @Override
    protected Observable resumeWithFallback() {
        return super.resumeWithFallback();
    }
}
