package com.example.currency_conversion.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.currency_conversion.TestConstant;
import com.example.currency_conversion.controllers.UserController;
import com.example.currency_conversion.dto.UserDTO;
import com.example.currency_conversion.entities.User;
import com.example.currency_conversion.exceptions.UserNotFoundException;
import com.example.currency_conversion.services.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTests {

  @Autowired
  private UserController userController;

  @MockBean
  private UserService userService;

  public List<User> createTestUsers() {

    User user = new User();
    user.setEmail("exampl@gmail.com");
    user.setName("Example");

    User user2 = new User();
    user2.setEmail("example2@gmail.com");
    user2.setName("Example 2");

    return Arrays.asList(user, user2);

  }

  public User createUser() {
    User user = new User();
    user.setEmail("example@example.com");
    user.setName("example");
    return user;
  }

  public UserDTO createUserDTO() {
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail("example@exampl.com");
    userDTO.setName("example");

    return userDTO;
  }

  @Test
  public void shouldReturnUsers() {
    List<User> testUsers = createTestUsers();

    when(userService.getAllUsers()).thenReturn(testUsers);

    ResponseEntity<Object> response = userController.getAllUsers();

    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals(HttpStatus.OK, responseBody.get("status"));
    assertEquals(TestConstant.GET_USERS_SUCCES, responseBody.get("message"));
    assertEquals(testUsers, responseBody.get("data"));
  }

  @Test
  public void shouldReturnExceptionWhenGetUserFails() throws Exception {
    RuntimeException exception = new RuntimeException("Failed to Fetch users");

    when(userService.getAllUsers()).thenThrow(exception);

    ResponseEntity<Object> response = userController.getAllUsers();
    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals("Failed to Fetch users", responseBody.get("message"));
  }

  @Test
  public void shouldCreateUserWithUserDTO() throws Exception {
    User user = createUser();
    UserDTO userDTO = createUserDTO();

    when(userService.createUser(any(User.class))).thenReturn(user);

    ResponseEntity<Object> response = userController.createUser(userDTO);
    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(TestConstant.USER_SAVED_SUCCESS, responseBody.get("message"));
    assertEquals(user, responseBody.get("data"));
  }

  @Test
  public void shouldReturnUpdatedUserOnSuccess() throws Exception {
    UserDTO userDto = createUserDTO();
    Long Id = (long) 1234;
    User user = createUser();

    when(userService.updateUser(Id, userDto)).thenReturn(user);

    ResponseEntity<Object> response = userController.updateUser(userDto, Id);

    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals(HttpStatus.OK, responseBody.get("status"));
    assertEquals(TestConstant.USER_UPDATED_SUCCESS, responseBody.get("message"));
    assertEquals(user, responseBody.get("data"));
  }

  @Test
  public void shouldSuccessfullyFetchUser() throws Exception {
    Long Id = (long) 123;
    User user = createUser();

    when(userService.findUser(Id)).thenReturn(user);

    ResponseEntity<Object> response = userController.fetchUser(Id);

    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals(HttpStatus.OK, responseBody.get("status"));
    assertEquals(TestConstant.USER_FETCH_SUCCESS, responseBody.get("message"));
    assertEquals(user, responseBody.get("data"));
  }

  @Test
  public void shouldHandleUserNotFoundExceptionGraceFully() throws Exception {
    Long Id = (long) 123;

    when(userService.findUser(Id)).thenThrow(new UserNotFoundException(TestConstant.USER_NOT_FOUND));

    ResponseEntity<Object> response = userController.fetchUser(Id);

    Map<?, ?> responseBody = (Map<?, ?>) response.getBody();

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(TestConstant.USER_NOT_FOUND, responseBody.get("message"));
    assertEquals(TestConstant.USER_NOT_FOUND, responseBody.get("data"));
  }

}
