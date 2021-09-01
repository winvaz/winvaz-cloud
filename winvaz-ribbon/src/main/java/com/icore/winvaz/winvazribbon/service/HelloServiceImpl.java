package com.icore.winvaz.winvazribbon.service;

import com.icore.winvaz.winvazribbon.model.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/3/30 14:52
 * @Version 1.0.0
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * @HystrixCommand注解更为优雅地定义Hystrix的实现，但注解的方法只是同步执行的实现。
     * 注解实现服务降级只需使用@HystrixCommand中的fallbackMethod参数来指定具体的服务降级实现方法。
     * @author wdq
     * @create 2021/3/31 11:31
     * @Return java.lang.String
     */
    /**
     *  异常传播
     *  通过@HystrixCommand注解的ignoreExceptions参数来设置忽略指定异常类型功能。
     *  当方法抛出DuplicateRequestException异常时，Hystrix会将它包装在HystrixBadRequestException中抛出。
     *  这样就不会触发后续的fallback逻辑
     */
    /**
     * 异常获取
     * 在fallback实现方法的参数中增加Throwable对象的定义，这样就可以在方法内部获取触发服务降级的具体异常内容了。
     */
    /**
     * 注解的形式设置命令名称(commandKey)、分组(groupKey以及线程池划分(threadPoolKey)
     */
    @Override
    @HystrixCommand(fallbackMethod = "indexFallback",
            commandKey = "indexKey",
            groupKey = "indexGroup",
            threadPoolKey = "indexThread",
            ignoreExceptions = Exception.class
    )
    public String index() {
        long start = System.currentTimeMillis();
        String result;
        try {
            result = restTemplate.getForEntity("http://WINVAZ-HELLO/winvaz-hello/index", String.class).getBody();
        } catch (RestClientException e) {
            // 增加Throwable对象的定义.
            throw new RuntimeException("index commannd failed", e);
        }
        log.info("Spend Time：{}", (System.currentTimeMillis() - start));

        return result;
    }

    /**
     * 注解实现请求合并器
     *
     * @param id
     * @throws
     * @author wdq
     * @create 2021/4/2 17:10
     * @Return com.icore.winvaz.winvazribbon.model.Person
     */
    @Override
    @HystrixCollapser(batchMethod = "findAll", collapserProperties = {@HystrixProperty(name =
            "timerDelayInMilliseconds", value = "100")})
    public Person find(Long id) {
        return restTemplate.getForObject("http://WINVAZ-HELLO/hello/person/{1}", Person.class, id);
    }

    @Override
    public List<Person> findAll(List<Long> ids) {
        return restTemplate.getForObject("http://WINVAZ-HELLO/hello/person?ids={1}", List.class, StringUtils.join(ids
                , ","));
    }

    /**
     * 如果fallbackMethod属性值的方法参数不一致会抛fallback method wasn't found: indexFallback([])异常
     *
     * @param e
     * @throws
     * @author wdq
     * @create 2021/4/7 09:26
     * @Return java.lang.String
     */
    @HystrixCommand(fallbackMethod = "defaultFallback")
    public String indexFallback(Throwable e) {
        // 此处可能是另外一个网络请求来获取，所有也有可能失败
        assert "index commannd failed".equals(e.getMessage());

        return "First Fallback Error";
    }

    public String defaultFallback() {
        return "Second Fallback Error";
    }

    /**
     * @param
     * @throws
     * @HystrixCommand注解异步执行
     * @author wdq
     * @create 2021/3/31 11:32
     * @Return java.lang.String
     */
    @HystrixCommand
    public Future<String> hystrixCommandGetIdAsync(final String id) {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://WINVAZ-HELLO/hello/index/id/{1}", String.class, id);
            }
        };
    }

    /**
     * HystrixObservableCommand的注解依然是@HystrixCommand
     *
     * @param
     * @throws
     * @author wdq
     * @create 2021/3/31 13:46
     * @Return java.lang.String
     */
    public Observable<String> hystrixObservableCommandGetId(final String id) {
        return Observable.unsafeCreate(subscriber -> {
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


}
