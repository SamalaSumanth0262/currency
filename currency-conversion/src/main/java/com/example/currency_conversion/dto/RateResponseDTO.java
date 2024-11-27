package com.example.currency_conversion.dto;

public class RateResponseDTO {

  public Data data;
  private String message;
  private String status;

  // Getters and Setters
  public Data getData() {
    return data;
  }

  public void setData(Data data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return ("RateResponseDTO{" +
        "data=" +
        data +
        ", message='" +
        message +
        '\'' +
        ", status='" +
        status +
        '\'' +
        '}');
  }

  // Inner class for the "data" field
  public static class Data {

    private String sourceCurrency;
    private String targetCurrency;
    private Double exchangeRate;

    // Getters and Setters
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

    public double getExchangeRate() {
      return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
      this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
      return ("Data{" +
          "sourceCurrency='" +
          sourceCurrency +
          '\'' +
          ", targetCurrency='" +
          targetCurrency +
          '\'' +
          ", exchangeRate=" +
          exchangeRate +
          '}');
    }
  }
}
