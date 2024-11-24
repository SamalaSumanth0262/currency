package com.api.rate_service.services;

import com.api.rate_service.dto.RateDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRateService {
  public Mono<Double> fetchRate(String sourceCurrency, String targetCurrency);
}
