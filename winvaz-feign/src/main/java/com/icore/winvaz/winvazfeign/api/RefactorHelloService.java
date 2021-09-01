package com.icore.winvaz.winvazfeign.api;

import com.icore.winvaz.winvazfeign.config.LoggerConfig;
import com.icore.winvaz.winvazhelloapi.api.WinVazHelloService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/4/8 18:43
 * @Version 1.0.0
 */
// 通过@FeignClient注解的configuration属性来指定对应的服务降级实现类
@FeignClient(value = "WINVAZ-HELLO", configuration = LoggerConfig.class)
public interface RefactorHelloService extends WinVazHelloService {
}
