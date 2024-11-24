package com.api.rate_service.services;

import com.api.rate_service.clients.ExternalRateClient;
import com.api.rate_service.dto.RateDTO;
import com.api.rate_service.dto.RateResponseDTO;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RateService implements IRateService {

  @Autowired
  ExternalRateClient externalRateClient;

  public Mono<Double> fetchRate(String sourceCurrency, String targetCurrency) {
    return externalRateClient
      .getRates()
      .flatMap(response -> {
        Double rate = response.getRates().get(targetCurrency);
        if (rate == null) {
          return Mono.error(
            new IllegalArgumentException("Target currency not found")
          );
        }
        return Mono.just(rate);
      });
  }
}
