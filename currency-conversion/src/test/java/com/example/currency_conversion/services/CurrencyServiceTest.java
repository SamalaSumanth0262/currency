package com.example.currency_conversion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.currency_conversion.client.RateServiceClient;
import com.example.currency_conversion.dto.ConvertDTO;
import com.example.currency_conversion.dto.RateServiceResponseDTO;
import com.example.currency_conversion.mocks.RateServiceResponseDTOMock;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class CurrencyServiceTest {

  @InjectMocks
  CurrencyService currencyService;

  @Mock
  private RateCacheService rateCacheService;

  @Mock
  private RateServiceClient rateServiceClient;

  @Mock
  private ConvertDTO convertDTO;

  @BeforeEach
  void setUp() {
    convertDTO = new ConvertDTO();
    convertDTO.setSourceAmount((double) 100);
    convertDTO.setSourceCurrency("USD");
    convertDTO.setTargetCurrency("INR");
  }

  @Test
  void shouldConvertWithCovertOrder() throws Exception {
    HashMap<String, Double> cachedRates = new HashMap<>();
    cachedRates.put(convertDTO.getTargetCurrency(), (double) 80);

    when(rateCacheService.getRatesBySource(convertDTO.getSourceCurrency())).thenReturn(cachedRates);
    ConvertDTO response = currencyService.convertOrder(convertDTO);

    assertEquals(8000, response.getTargetAmount());
  }

  @Test
  void shouldFetchRatesUsingRateServiceClient() throws Exception {
    RateServiceResponseDTO mockResponse = RateServiceResponseDTOMock.createMockResponse();

    when(rateCacheService.getRatesBySource(convertDTO.getSourceCurrency())).thenReturn(null);
    when(rateServiceClient.fetchRates()).thenReturn(Mono.just(mockResponse));

    Double rate = currencyService.fetchRate(convertDTO);

    assertEquals(mockResponse.getData().getRates().get(convertDTO.getTargetCurrency()), rate);
  }
}
