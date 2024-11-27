package com.example.currency_conversion.services;

import java.time.Duration;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RateCacheService {

  private final RedisTemplate<String, Map<String, Double>> redisTemplate;

  private final HashOperations<String, String, Map<String, Double>> hashOperations;

  private static String cacheKeyRates = "rates";

  public RateCacheService(
    RedisTemplate<String, Map<String, Double>> redisTemplate
  ) {
    this.redisTemplate = redisTemplate;
    this.hashOperations = redisTemplate.opsForHash();
  }

  public void saveRates(String base, Map<String, Double> rates) {
    hashOperations.put(cacheKeyRates, base, rates);

    redisTemplate.expire(cacheKeyRates, Duration.ofMinutes(30));
  }

  public Map<String, Double> getRatesBySource(String base) {
    Map<String, Double> cachedRates = hashOperations.get(cacheKeyRates, base);

    return cachedRates;
  }
}
