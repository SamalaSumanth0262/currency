package com.api.rate_service.clients;

import com.api.rate_service.dto.RateResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ExternalRateClient implements IExternalRateClient {

  private static String baseUrl =
    "https://openexchangerates.org/api/latest.json";
  private static String openExchangeRateApiSecret =
    "e5d58eb2d1ef4b99b8322fc9c4a72302";

  @Override
  public Mono<RateResponseDTO> getRates() {
    WebClient openExchangeRateClient = WebClient
        .builder()
        .baseUrl(baseUrl)
        .build();

    return openExchangeRateClient
        .get()
        .uri(uriBuilder -> uriBuilder.queryParam("app_id", openExchangeRateApiSecret).build())
        .retrieve()
        .bodyToMono(RateResponseDTO.class)
        .onErrorResume(error -> {
          return Mono.empty();
        });
  }

}
