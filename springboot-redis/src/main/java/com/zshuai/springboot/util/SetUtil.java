package com.zshuai.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SetUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void add(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void remove(String key, Object... value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 随机移除,并返回移除的元素
     *
     * @param key
     * @return
     */
    public Object pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素从一个集合移动到另一个集合
     */
    public void move(String key, Object value, String destKey) {
        redisTemplate.opsForSet().move(key, value, destKey);
    }
}
