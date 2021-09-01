package com.icore.winvaz.winvazribbon.service;

import com.icore.winvaz.winvazribbon.model.Person;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

/**
 * @Deciption 创建请求命令，实现HystrixCommand
 * @Author wdq
 * @Create 2021/3/31 11:24
 * @Version 1.0.0
 */
public class HelloHystrixCommand extends HystrixCommand {

    private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("HelloCommandKey");

    private RestTemplate restTemplate;
    private Long id;

    /**
     * 命令名称，分组以及线程池划分
     * 继承的方式实现hystrix命令使用类名作为默认的命令名称。可以在构造函数中通过Setter静态类来设置
     *
     * @param setter
     * @param restTemplate
     * @param id
     * @throws
     * @author wdq
     * @create 2021/4/1 11:37
     * @Return
     */
    public HelloHystrixCommand(Setter setter, RestTemplate restTemplate, Long id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloCommandGroup"))
                // .andCommandKey(HystrixCommandKey.Factory.asKey("HelloName"))
                .andCommandKey(GETTER_KEY)
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolKey")));
        this.restTemplate = restTemplate;
        this.id = id;
    }


    @Override
    protected Object run() throws Exception {
        return restTemplate.getForObject("http://WINVAZ-HELLO/hello/index/id/{1}",
                Long.class, id);
    }

    /**
     * 通过重载getCacheKey()方法开启缓存
     *
     * @param
     * @throws
     * @author wdq
     * @create 2021/4/1 13:51
     * @Return java.lang.String
     */
    @Override
    protected String getCacheKey() {
        // 根据ID置入缓存
        return String.valueOf(id);
    }

    public static void flushCache(Long id) {
        // 刷新缓存，根据ID进行清理
        HystrixRequestCache.getInstance(GETTER_KEY, HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
    }

    /**
     * 定义服务降级
     *
     * @throws
     * @author wdq
     * @create 2021/3/31 13:54
     * @Return
     */
    @Override
    protected Object getFallback() {
        // 获取具体的异常
        Throwable executionException = getExecutionException();
        return super.getFallback();
    }
}

/**
 * 清理失效缓存功能
 * 上类是获取
 * 此类事提交
 * @author wdq
 * @create 2021/4/1 15:56
 * @Return
 * @exception
 */
class HelloHystrixPostCommand extends HystrixCommand {

    private RestTemplate restTemplate;
    private Person person;

    public HelloHystrixPostCommand(RestTemplate restTemplate, Person person) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HelloCommandKey")));
        this.restTemplate = restTemplate;
        this.person = person;
    }

    @Override
    protected Object run() throws Exception {
        // 写操作
        Person person = restTemplate.postForObject("http://WINVAZ-HELLO/hello/index/persons", this.person,
                Person.class);
        // 刷新缓存，清理缓存中失效的person
        HelloHystrixCommand.flushCache(person.getId());

        return person;
    }
}
