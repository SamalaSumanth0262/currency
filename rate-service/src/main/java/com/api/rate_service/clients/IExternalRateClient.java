package com.api.rate_service.clients;

import com.api.rate_service.dto.RateResponseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IExternalRateClient {
  public Mono<RateResponseDTO> getRates();
}
