package com.example.currency_conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // Include base package

public class CurrencyConversionApplication {

  public static void main(String[] args) {
    SpringApplication.run(CurrencyConversionApplication.class, args);
  }
}
