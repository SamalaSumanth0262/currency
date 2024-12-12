package com.example.currency_conversion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

  private User user;
  private UserDTO userDTO;
  private List<User> userList;

  @BeforeEach
  void setUp() {
    user = new User();
    user.setEmail("example@gmail.com");
    user.setName("example");
    user.setId(123L);

    userDTO = new UserDTO();
    userDTO.setEmail(user.getEmail());
    userDTO.setName(user.getName());

    // Create a sample list of users
    userList = new ArrayList<>();
    userList.add(user);

    User user2 = new User();
    user2.setEmail("example2@gmail.com");
    user2.setName("example 2");
    userList.add(user2);
  }

  @Test
  void shouldReturnAllUsers() {
    when(userRepository.findAll()).thenReturn(userList);

    List<User> users = userService.getAllUsers();

    assertEquals(userList, users);
    assertEquals(2, users.size());
    assertEquals("example@gmail.com", users.get(0).getEmail());
  }

  @Test
  void shouldReturnUserByCreating() {
    User firstUser = userList.get(0);
    when(userService.createUser(any(User.class))).thenReturn(firstUser);

    User createdUser = userService.createUser(firstUser);

    assertEquals(user, createdUser);
    assertEquals("example@gmail.com", user.getEmail());
  }

  @Test
  void shouldUpdateUserSuccess() throws Exception {
    Long Id = (long) 123;

    when(userRepository.findById(Id)).thenReturn(Optional.ofNullable(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    User updatedUser = userService.updateUser(Id, userDTO);

    assertEquals(user, updatedUser);
  }

  @Test
  void shouldHandleUserNotFoundException() {
    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.updateUser(user.getId(), userDTO));
  }

  @Test
  void shouldReturnUserSuccess() throws Exception {
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    User expectedUser = userService.findUser(user.getId());

    assertEquals(expectedUser, user);

  }

  @Test
  void shouldHandleUserNotFoundExceptionWhenFetchingTheUser() {
    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.findUser(user.getId()));
  }

}
