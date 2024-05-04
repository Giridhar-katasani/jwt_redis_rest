package com.rest.demo.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {

    private final StringRedisTemplate redisTemplate;

    public RedisTokenService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String username, String token) {
        redisTemplate.opsForValue().set(username, token);
    }

    public String getToken(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    public void deleteToken(String username) {
        redisTemplate.delete(username);
        System.out.println(redisTemplate.opsForValue().get("username"));
    }
}

