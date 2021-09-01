package com.icore.winvaz.winvazcommon.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.icore.winvaz.winvazcommon.service.RedisService;
import com.icore.winvaz.winvazcommon.service.impl.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2020/7/21 17:59
 * @Version 1.0.0
 */
@Configuration
public class BaseRedisConfig implements Config {

    /**
     * 过期时间
     */
    @Value("${redis.expire.common}")
    private Duration timeToLive = Duration.ZERO;

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {

        RedisSerializer<Object> redisSerializer = redisSerializer();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        RedisTemplate<?, ?> template = new RedisTemplate<>();

        template.setConnectionFactory(factory);
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.setHashKeySerializer(redisSerializer);
        template.setHashValueSerializer(stringRedisSerializer);
        template.setDefaultSerializer(redisSerializer);
        template.afterPropertiesSet();

        return template;
    }

    @Bean
    public RedisSerializer<Object> redisSerializer() {
        // 创建JSON序列化器
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);

        serializer.setObjectMapper(objectMapper);

        return serializer;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);

        return new RedisCacheManager(redisCacheWriter, this.redisCacheConfiguration());
    }

    /**
     * Redis全局缓存配置
     * @author wdq
     * @create 2021/3/25 16:51
     * @param
     * @Return org.springframework.data.redis.cache.RedisCacheConfiguration
     * @exception
     */
    private RedisCacheConfiguration redisCacheConfiguration() {
        // 全局的Redis缓存配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                // 设置Redis缓存有效期(单位:秒)
                .entryTtl(timeToLive)
                // 使用StringRedisSerializer来序列化和反序列化redis的key值
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()))
                // 禁用空值
                .disableCachingNullValues();

        return redisCacheConfiguration;
    }

    @Bean
    public RedisService redisService() {
        return new RedisServiceImpl();
    }
}