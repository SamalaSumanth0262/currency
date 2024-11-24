package com.api.rate_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rate_service.dto.RateDTO;
import com.api.rate_service.services.RateService;
import com.api.rate_service.utils.ResponseHandler;

@RestController
@RequestMapping("/api/v1/rate")
public class RateController {

  @Autowired
  RateService rateService;

  @PostMapping
  public ResponseEntity<Object> getConversionRate(@RequestBody RateDTO rateDTO) {
    Double exchangeRate = rateService.fetchRate(rateDTO.getSourceCurrency(), rateDTO.getTargetCurrency());
    rateDTO.setExchangeRate(exchangeRate);

    return ResponseHandler.generateRespons(HttpStatus.OK, "Converstion Rate successful", rateDTO);
  }
}
