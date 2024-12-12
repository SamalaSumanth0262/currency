package com.example.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.currency_conversion.dto.UserDTO;
import com.example.currency_conversion.entities.User;
import com.example.currency_conversion.exceptions.UserNotFoundException;
import com.example.currency_conversion.repository.UserRepository;
import com.example.currency_conversion.services.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  UserService userService;

  @Mock
  UserRepository userRepository;

  public List<User> listAllUsers() {

    User user1 = new User();
    user1.setEmail("example@gmail.com");
    user1.setName("example");

    User user2 = new User();
    user2.setEmail("Example2@gamil.com");
    user2.setName("example 2");

    return new ArrayList<User>(Arrays.asList(user1, user2));
  }

  @Test
  void shouldReturnAllUsers() {

    when(userRepository.findAll()).thenReturn(listAllUsers());

    List<User> users = userService.getAllUsers();

    assertEquals(listAllUsers(), users);
  }

  @Test
  void shouldReturnUserByCreating() {
    User user = listAllUsers().get(0);
    when(userService.createUser(any(User.class))).thenReturn(user);

    User createdUser = userService.createUser(user);

    assertEquals(user, createdUser);
  }

  @Test
  void shouldUpdateUserSuccess() throws Exception {
    User user = listAllUsers().get(0);
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(user.getEmail());
    userDTO.setName(user.getName());

    Long Id = (long) 123;

    when(userRepository.findById(Id)).thenReturn(Optional.ofNullable(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    User updatedUser = userService.updateUser(Id, userDTO);

    assertEquals(user, updatedUser);
  }

  @Test
  void shouldHandleUserNotFoundException() {
    User user = listAllUsers().get(0);
    Long Id = (long) 123;
    UserDTO userDTO = new UserDTO();
    userDTO.setEmail(user.getEmail());
    userDTO.setName(user.getName());

    when(userRepository.findById(Id)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.updateUser(Id, userDTO));
  }

  @Test
  void shouldReturnUserSuccess() throws Exception {
    Long Id = (long) 123;
    User user = listAllUsers().get(0);
    when(userRepository.findById(Id)).thenReturn(Optional.of(user));

    User expectedUser = userService.findUser(Id);

    assertEquals(expectedUser, user);

  }

  @Test
  void shouldHandleUserNotFoundExceptionWhenFetchingTheUser() {
    Long Id = (long) 123;
    when(userRepository.findById(Id)).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.findUser(Id));
  }

}
