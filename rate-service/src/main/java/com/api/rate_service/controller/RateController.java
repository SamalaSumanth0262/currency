package com.api.rate_service.controller;

import com.api.rate_service.dto.RateDTO;
import com.api.rate_service.services.RateService;
import com.api.rate_service.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/rate")
public class RateController {

  @Autowired
  RateService rateService;

  @PostMapping
  public Mono<ResponseEntity<Object>> getConversionRate(
    @RequestBody RateDTO rateDTO
  ) {
    return rateService
      .fetchRate(rateDTO.getSourceCurrency(), rateDTO.getTargetCurrency())
      .map(exchangeRate -> {
        rateDTO.setExchangeRate(exchangeRate);
        return ResponseHandler.generateResponse(
          HttpStatus.OK,
          "Converstion Rate successful",
          rateDTO
        );
      });
  }
}
