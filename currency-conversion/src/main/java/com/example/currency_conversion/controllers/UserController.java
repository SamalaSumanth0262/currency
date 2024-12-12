package com.example.currency_conversion.controllers;

import com.example.currency_conversion.dto.UserDTO;
import com.example.currency_conversion.entities.User;
import com.example.currency_conversion.exceptions.UserNotFoundException;
import com.example.currency_conversion.services.UserService;
import com.example.currency_conversion.utils.ResponseHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<Object> getAllUsers() {
    try {
      List<User> users = userService.getAllUsers();

      return ResponseHandler.generateResponse(
          HttpStatus.OK,
          "All users fetched successfully",
          users);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(
          HttpStatus.BAD_REQUEST,
          e.getMessage(),
          null);
    }

  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody UserDTO userDTO) {
    try {
      User user = new User();
      user.setEmail(userDTO.getEmail());
      user.setName(userDTO.getName());
      return ResponseHandler.generateResponse(
          HttpStatus.OK,
          "User Saved Succesfully",
          userService.createUser(user));
    } catch (Exception e) {
      return ResponseHandler.generateResponse(
          HttpStatus.BAD_REQUEST,
          e.getMessage(),
          null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateUser(
      @RequestBody UserDTO userDTO,
      @PathVariable Long id) {
    try {
      return ResponseHandler.generateResponse(
          HttpStatus.OK,
          "Updated Successfully",
          userService.updateUser(id, userDTO));
    } catch (Exception e) {
      return ResponseHandler.generateResponse(
          HttpStatus.BAD_REQUEST,
          e.getMessage(),
          userDTO);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> fetchUser(@PathVariable Long id) {
    try {
      User user = userService.findUser(id);
      return ResponseHandler.generateResponse(
          HttpStatus.OK,
          "Successfully Fetched User",
          user);
    } catch (UserNotFoundException e) {
      return ResponseHandler.generateResponse(
          HttpStatus.BAD_REQUEST,
          "User not found",
          e.getMessage());
    }
  }
}
