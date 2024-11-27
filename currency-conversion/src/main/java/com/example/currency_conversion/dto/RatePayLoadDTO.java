package com.example.currency_conversion.dto;

import org.springframework.stereotype.Component;

@Component
public class RatePayLoadDTO {
  private String sourceCurrency;
  private String targetCurrency;
  public String getSourceCurrency() {
    return sourceCurrency;
  }
  public void setSourceCurrency(String sourceCurrency) {
    this.sourceCurrency = sourceCurrency;
  }
  public String getTargetCurrency() {
    return targetCurrency;
  }
  public void setTargetCurrency(String targetCurrency) {
    this.targetCurrency = targetCurrency;
  }

  
}
