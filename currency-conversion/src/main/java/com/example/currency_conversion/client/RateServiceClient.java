package com.example.currency_conversion.client;

import com.example.currency_conversion.dto.RateServiceResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RateServiceClient {

  public static String rateServiceClientUrl = "http://localhost:8080/api/v1/gateway/rates";

  public Mono<RateServiceResponseDTO> fetchRates() {
    WebClient ratesClient = WebClient
        .builder()
        .baseUrl(rateServiceClientUrl)
        .build();

    return ratesClient
        .get()
        .retrieve()
        .bodyToMono(RateServiceResponseDTO.class)
        .onErrorResume(err -> {
          return Mono.empty();
        });
  }
}
