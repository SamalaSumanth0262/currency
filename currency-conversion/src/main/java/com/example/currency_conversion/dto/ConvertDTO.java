package com.example.currency_conversion.dto;

public class ConvertDTO {

  private String sourceCurrency;
  private String targetCurrency;
  private Double sourceAmount;
  private Double targetAmount;

  public Double getTargetAmount() {
    return targetAmount;
  }

  public void setTargetAmount(Double targetAmount) {
    this.targetAmount = targetAmount;
  }

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

  public Double getSourceAmount() {
    return sourceAmount;
  }

  public void setSourceAmount(Double sourceAmount) {
    this.sourceAmount = sourceAmount;
  }
}
