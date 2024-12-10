package com.example.currency_conversion.controllers;

import com.example.currency_conversion.dto.ConvertDTO;
import com.example.currency_conversion.services.CurrencyService;
import com.example.currency_conversion.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CurrencyController {

  @Autowired
  CurrencyService currencyService;

  @PostMapping("/api/v1/convert")
  public ResponseEntity<Object> convert(@RequestBody ConvertDTO convertDTO) throws Exception {
    try {
      return ResponseHandler.generateResponse(HttpStatus.OK, "converted", currencyService.convertOrder(convertDTO));
    } catch (Exception err) {
      return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Oops this wasnt suppose to happen",
          err.getMessage());
    }
  }
}
