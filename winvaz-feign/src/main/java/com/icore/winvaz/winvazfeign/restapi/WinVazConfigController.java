package com.icore.winvaz.winvazfeign.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试ConfigServer服务配置文件
 *
 * @Author wdq
 * @Create 2021/6/1 16:34
 * @Version 1.0.0
 */
@RefreshScope
@RestController
@RequestMapping("/winvaz-config")
public class WinVazConfigController {

    /**
     * 除了通过@Value注解绑定注入之外，也可以通过Environment对象来获取配置属性
     */
    /*
    @Autowired
    private Environment env;
    */

    @Value("${from}")
    private String from;

    @GetMapping("/from")
    public String from() {
        return from;
        // return env.getProperty("from", "undefined"); // git-dev-1.0
    }
}
