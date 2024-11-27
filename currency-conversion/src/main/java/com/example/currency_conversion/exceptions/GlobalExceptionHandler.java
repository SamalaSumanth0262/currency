package com.example.currency_conversion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.currency_conversion.utils.ResponseHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleBadRequest(HttpMessageNotReadableException exception) {
    return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "JSON is malformed", exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> genericException(Exception ex) {
    return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, null, ex.getMessage());
  }

}
