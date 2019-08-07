package com.restservice.configure;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;

/**
 * Created by liudeyu on 2019/7/2.
 */
@Configuration
@EnableCaching
public class RedisConfigure extends CachingConfigurerSupport {


    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                StringBuilder builder = new StringBuilder();
                builder.append(o.getClass().toString()).append(method.getName());
                for (Object object : objects) {
                    builder.append(object.toString());
                }
                return builder.toString();
            }
        };
    }


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        //rcm.setDefaultExpiration(60);//秒
        return rcm;
    }


    @Bean(name = "redis_mapper_with_json")
    public RedisTemplate<String, String> redisemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(mapper);
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;

    }


    @Bean(name = "redis_mapper_with_stream")
    public RedisTemplate<String, Object> rediObjectTemp(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> objectRedisTemplate = new RedisTemplate<>();
        StringRedisSerializer keySerial = new StringRedisSerializer();
        RedisObjectSerializer valueObje = new RedisObjectSerializer();
        objectRedisTemplate.setKeySerializer(keySerial);
        objectRedisTemplate.setValueSerializer(valueObje);
        objectRedisTemplate.setConnectionFactory(factory);
        return objectRedisTemplate;
    }
}
