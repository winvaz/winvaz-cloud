package com.icore.winvaz.winvazcommon.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author wdq
 * @Create 2021/6/4 09:19
 * @Version 1.0.0
 */
public final class BeansRegistry implements ApplicationContextAware {

    private static ApplicationContext CONTEXT;

    public static ApplicationContext context() {
        return CONTEXT;
    }

    public static <T> T getBean(Class<T> beanType, String name) {
        return CONTEXT.getBean(beanType, name);
    }

    public static <T> T getBean(Class<T> beanType) {
        return CONTEXT.getBean(beanType);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }
}
