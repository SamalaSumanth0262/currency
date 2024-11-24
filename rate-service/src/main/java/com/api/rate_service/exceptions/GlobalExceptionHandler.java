package com.api.rate_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.rate_service.utils.ResponseHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleGenericError(Exception ex) {
    return ResponseHandler.generateRespons(HttpStatus.BAD_REQUEST, "Bad request for the api", ex.getMessage());
  }
}
