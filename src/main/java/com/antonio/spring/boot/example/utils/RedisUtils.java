package com.antonio.spring.boot.example.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.TimeUnit;

public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object value, long seconds) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.opsForValue().get(key);
    }

    public boolean del(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate.delete(key);
    }
}
