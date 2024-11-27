package com.example.currency_conversion.services;

import com.example.currency_conversion.client.RateServiceClient;
import com.example.currency_conversion.dto.ConvertDTO;
import com.example.currency_conversion.dto.RateServiceResponseDTO;
import com.example.currency_conversion.interfaces.ICurrencyService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService implements ICurrencyService {

  @Autowired
  RateServiceClient rateServiceClient;

  @Autowired
  RateCacheService rateCacheService;

  public ConvertDTO convertOrder(ConvertDTO convertDTO) throws Exception {
    Double targetAmount = convertDTO.getSourceAmount() * fetchRate(convertDTO);
    System.out.println("targetAmount: " + targetAmount);

    convertDTO.setTargetAmount(targetAmount);
    return convertDTO;
  }

  public Double fetchRate(ConvertDTO convertDTO) throws Exception {
    try {
      new Exception("Something went wrong in here");
      Map<String, Double> cachedRatesBySource = rateCacheService.getRatesBySource(
          convertDTO.getSourceCurrency());

      if (cachedRatesBySource == null) {
        Mono<RateServiceResponseDTO> fetchedRates = rateServiceClient.fetchRates();
        Map<String, Double> rates = fetchedRates.block().getData().getRates();
        rateCacheService.saveRates(convertDTO.getSourceCurrency(), rates);

        return rates.get(convertDTO.getTargetCurrency());
      } else {
        return cachedRatesBySource.get(convertDTO.getTargetCurrency());
      }
    } catch (Exception er) {
      throw new RuntimeException("Failed to fetch rates: " + er.getMessage());
    }
  }
}
