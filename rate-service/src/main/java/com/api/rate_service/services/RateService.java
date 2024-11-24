package com.api.rate_service.services;

import org.springframework.stereotype.Service;

import com.api.rate_service.dto.RateDTO;

@Service
public class RateService implements IRateService{

  public Double fetchRate(String sourceCurrency, String targetCurrency) {
    return 123.123;
  }
}
