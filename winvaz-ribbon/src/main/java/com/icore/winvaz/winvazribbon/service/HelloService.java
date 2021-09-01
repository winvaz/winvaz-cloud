package com.icore.winvaz.winvazribbon.service;

import com.icore.winvaz.winvazribbon.model.Person;

import java.util.List;

/**
 * @Deciption 业务接口层
 * @Author wdq
 * @Create 2021/3/30 14:52
 * @Version 1.0.0
 */
public interface HelloService {

    String index();

    Person find(Long id);

    List<Person> findAll(List<Long> ids);
}
