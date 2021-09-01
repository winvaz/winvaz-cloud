package com.icore.winvaz.winvazribbon;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
// 开启断路器功能
@EnableCircuitBreaker
// @EnableDiscoveryClient 注解让该应用注册为Eureka客户端应用，以获取服务发现的能力。
@EnableDiscoveryClient
@SpringBootApplication
// 也可以@SpringCloudApplication代替上面三个注解
// @SpringCloudApplication
public class WinvazRibbonApplication {

    /**
     *  创建RestTemplate的Spring Bean实例
     * 通过@LoadBalanced注解开启客户端负载均衡
     * @author wdq
     * @create 2021/3/20 19:12
     * @param
     * @Return org.springframework.web.client.RestTemplate
     * @exception
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(WinvazRibbonApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}
