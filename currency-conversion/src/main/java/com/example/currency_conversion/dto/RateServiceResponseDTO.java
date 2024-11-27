package com.example.currency_conversion.dto;

import java.util.Map;

public class RateServiceResponseDTO {

  private Data data;
  private String message;
  private String status;

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

  public static class Data {

    private String disclaimer;
    private String license;
    private String timeStamp;
    private String base;
    private Map<String, Double> rates;

    public String getDisclaimer() {
      return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
      this.disclaimer = disclaimer;
    }

    public String getLicense() {
      return license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getTimeStamp() {
      return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
      this.timeStamp = timeStamp;
    }

    public String getBase() {
      return base;
    }

    public void setBase(String base) {
      this.base = base;
    }

    public Map<String, Double> getRates() {
      return rates;
    }

    public void setRates(Map<String, Double> rates) {
      this.rates = rates;
    }
  }
}
