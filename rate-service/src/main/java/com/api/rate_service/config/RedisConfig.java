package com.api.rate_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.api.rate_service.dto.RateResponseDTO;

@Configuration
public class RedisConfig {
  

  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory("localhost", 6379);
  }

  public RedisTemplate<String, RateResponseDTO> redisTemplate() {
    RedisTemplate<String, RateResponseDTO> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.afterPropertiesSet();
    
    return redisTemplate;
  }
}
