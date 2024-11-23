package api.conversion_gateway;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RepsonseHandler {
  public static ResponseEntity<Object> generateReponse(HttpStatus httpStatus, String message, Object data) {
    HashMap<String, Object> response = new HashMap<>();
    response.put("status", httpStatus);
    response.put("message", message);
    response.put("data", data);
    
    return new ResponseEntity<>(response, httpStatus);
  }
}
