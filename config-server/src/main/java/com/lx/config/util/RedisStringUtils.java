package com.lx.config.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisStringUtils {

    /**
     * 用户缓存前缀
     */
    public static final String USER_CACHE_PREFIX=  "user:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setKey(String key,String value,int expireSeconds){
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.set(key,value,expireSeconds);
    }

    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}