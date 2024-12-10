package com.example.currency_conversion.contoller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.currency_conversion.TestConstant;
import com.example.currency_conversion.controllers.CurrencyController;
import com.example.currency_conversion.dto.ConvertDTO;
import com.example.currency_conversion.services.CurrencyService;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTests {

  @SuppressWarnings("removal")
  @MockBean
  private CurrencyService currencyService;

  @Autowired
  private CurrencyController currencyController;

  private ConvertDTO createConvertDTO() {
    ConvertDTO convertDTO = new ConvertDTO();

    convertDTO.setSourceAmount(100.00);
    convertDTO.setSourceCurrency("USD");
    convertDTO.setTargetCurrency("INR");
    convertDTO.setTargetAmount(9000.00);
    return convertDTO;
  }

  @Test
  public void shouldReturnResponseWhenConversionSucceeds() throws Exception {
    ConvertDTO convertDTO = createConvertDTO();

    when(currencyService.convertOrder(convertDTO)).thenReturn(convertDTO);

    ResponseEntity<Object> response = currencyController.convert(convertDTO);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();
    assertEquals(TestConstant.CONVERT_SUCCESS, responseBody.get("message"));
    assertEquals(convertDTO, responseBody.get("data"));

  }

  @Test
  public void shouldReturnExceptionWhenConversionFails() throws Exception {
    ConvertDTO convertDTO = createConvertDTO();
    Exception exception = new Exception("Error");

    when(currencyService.convertOrder(convertDTO))
        .thenThrow(exception);

    ResponseEntity<Object> response = currencyController.convert(convertDTO);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(TestConstant.ERROR_MESSAGE, ((Map<?, ?>) response.getBody()).get("message"));

  }
}
