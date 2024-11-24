package com.api.rate_service.utils;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
  private ResponseHandler() {
  }

  public static ResponseEntity<Object> generateRespons(HttpStatus httpStatus, String message, Object data) {
    HashMap<String, Object> response = new HashMap<>();
    response.put("status", httpStatus);
    response.put("message", message);
    response.put("data", data);

    return new ResponseEntity<>(response, httpStatus);
  }
}
