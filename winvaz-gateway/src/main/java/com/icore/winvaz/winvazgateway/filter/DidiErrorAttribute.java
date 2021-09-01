package com.icore.winvaz.winvazgateway.filter;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义异常信息
 * @Author wdq
 * @Create 2021/6/1 10:25
 * @Version 1.0.0
 */
@Component
public class DidiErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
        result.remove("exception");
        return result;
    }
}
