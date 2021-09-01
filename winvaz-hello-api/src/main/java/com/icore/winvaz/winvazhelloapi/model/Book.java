package com.icore.winvaz.winvazhelloapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/3/18 10:34
 * @Version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    // @Value(“${xxxx}”)注解从配置文件读取值的用法
    @Value("${book.name}")
    private String name;

    // @Value(“#{}”) 表示SpEl表达式通常用来获取bean的属性，或者调用bean的某个方法。当然还有可以表示常量
    @Value("#{book.author}")
    private String author;
}