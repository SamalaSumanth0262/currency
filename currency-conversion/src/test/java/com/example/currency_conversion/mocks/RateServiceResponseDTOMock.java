package com.example.currency_conversion.mocks;

import java.util.HashMap;
import java.util.Map;

import com.example.currency_conversion.dto.RateServiceResponseDTO;

public class RateServiceResponseDTOMock {

  public static RateServiceResponseDTO createMockResponse() {
      RateServiceResponseDTO mockResponse = new RateServiceResponseDTO();
      
      RateServiceResponseDTO.Data mockData = new RateServiceResponseDTO.Data();
      mockData.setDisclaimer("Mock Disclaimer");
      mockData.setLicense("Mock License");
      mockData.setTimeStamp("2024-12-12T10:00:00Z");
      mockData.setBase("USD");
      
      Map<String, Double> mockRates = new HashMap<>();
      mockRates.put("INR", 80.0);
      mockRates.put("EUR", 0.85);
      mockRates.put("GBP", 0.75);
      mockData.setRates(mockRates);
      
      mockResponse.setData(mockData);
      mockResponse.setMessage("Success");
      mockResponse.setStatus("200");

      return mockResponse;
  }
}