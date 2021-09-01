package com.icore.winvaz.winvazgateway;

import com.icore.winvaz.winvazgateway.filter.DidiFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

// 通过@EnableZuulProxy开启Zuul的API网关服务功能
@EnableZuulProxy
// 使用@RefreshScope注解来使Zuul的配置动态化
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class WinvazGatewayApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WinvazGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 应用主类中
        FilterProcessor.setProcessor(new DidiFilterProcessor());
    }

    /**
     * 使用@RefreshScope注解来使Zuul的配置动态化
     * @author wdq
     * @create 2021/6/1 11:38
     * @param
     * @Return org.springframework.cloud.netflix.zuul.filters.ZuulProperties
     * @exception
     */
    /*
    @Bean
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
    */
    // 自定义的过滤器创建具体的Bean才能启动该过滤器
    /*@Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }*/

    /**
     * Zuul路由规则通过-分隔的规范来定义服务名和服务版本标识
     */
    /*@Bean
    public PatternServiceRouteMapper patternServiceRouteMapper() {
        return new PatternServiceRouteMapper(
                "(?<name>^.+)-(?<version>v.+$)",
                "${version}/${name}"
        );
    }*/
}
