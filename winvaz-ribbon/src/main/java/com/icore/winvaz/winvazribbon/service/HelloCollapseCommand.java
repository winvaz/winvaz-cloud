package com.icore.winvaz.winvazribbon.service;

import com.icore.winvaz.winvazribbon.model.Person;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Deciption 通过继承HystrixCollapser实现请求合并器
 * @Author wdq
 * @Create 2021/4/1 17:14
 * @Version 1.0.0
 */
public class HelloCollapseCommand extends HystrixCollapser<List<Person>, Person, Long> {

    private HelloService helloService;
    private Long personId;

    public HelloCollapseCommand(HelloService helloService, Long personId) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("helloCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.helloService = helloService;
        this.personId = personId;
    }

    @Override
    public Long getRequestArgument() {
        return personId;
    }

    @Override
    protected HystrixCommand<List<Person>> createCommand(Collection<CollapsedRequest<Person, Long>> collapsedRequests) {
        List<Long> personIds = new ArrayList<>(collapsedRequests.size());
        personIds.addAll(collapsedRequests.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
        return new HelloBatchCommand(helloService,personIds);
    }

    @Override
    protected void mapResponseToRequests(List<Person> batchResponse,
                                         Collection<CollapsedRequest<Person, Long>> collapsedRequests) {
        int count = 0;
        for (CollapsedRequest<Person, Long> collapsedRequest : collapsedRequests) {
            Person person = batchResponse.get(count++);
            collapsedRequest.setResponse(person);
        }
    }
}
