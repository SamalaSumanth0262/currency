package com.example.currency_conversion.interfaces;

import java.util.List;

import com.example.currency_conversion.dto.UserDTO;
import com.example.currency_conversion.entities.User;
import com.example.currency_conversion.exceptions.UserNotFoundException;

public interface IUserService {
  public List<User> getAllUsers();

  public User createUser(User user);

  public User updateUser(Long id, UserDTO userDTO) throws UserNotFoundException;
}
