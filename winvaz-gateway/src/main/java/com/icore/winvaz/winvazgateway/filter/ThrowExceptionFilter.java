package com.icore.winvaz.winvazgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 *  测试Zuul异常过滤器
 * @Author wdq
 * @Create 2021/5/31 17:00
 * @Version 1.0.0
 */
@Slf4j
@Component
public class ThrowExceptionFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器中处理异常使用try-catch处理,参考route过滤器的RibbonRoutingFilter的run()方法
     * @author wdq
     * @create 2021/5/31 17:10
     * @param
     * @Return java.lang.Object
     * @exception
     */
    @Override
    public Object run() throws ZuulException {
        log.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
        }
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }
}
