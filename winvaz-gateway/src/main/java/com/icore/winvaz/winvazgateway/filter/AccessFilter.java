package com.icore.winvaz.winvazgateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个简单的Zuul过滤器
 * 实现在请求被路由之前检查HttpServletRequest中是否有accessToken参数，
 * 若有就进行路由，否则返回401 Unauthorized错误
 *
 * @Author wdq
 * @Create 2021/5/27 15:56
 * @Version 1.0.0
 */
@Slf4j
public class AccessFilter extends ZuulFilter {
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

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}", request.getMethod(), request.getRequestURI().toString());
        String token = request.getParameter("Authorized-Token");
        if (token == null) {
            log.warn("authorized token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
