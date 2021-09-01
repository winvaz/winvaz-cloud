package com.icore.winvaz.winvazcommon.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @Author wdq
 * @Create 2021/6/4 09:21
 * @Version 1.0.0
 */
@Configuration
public class BeansRegistryConfig implements Config{

    @Bean
    public BeansRegistry beansRegistry() {
        return new BeansRegistry();
    }

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        String pattern = "yyyy-MM-dd HH:mm:ss";
        mapper.setDateFormat(new SimpleDateFormat(pattern));

        return mapper;
    }
}
