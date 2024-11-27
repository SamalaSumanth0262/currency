package com.example.currency_conversion.services;

import com.example.currency_conversion.dto.UserDTO;
import com.example.currency_conversion.entities.User;
import com.example.currency_conversion.exceptions.UserNotFoundException;
import com.example.currency_conversion.interfaces.IUserService;
import com.example.currency_conversion.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

  @Autowired
  public UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long id, UserDTO userDTO)
      throws UserNotFoundException {
    User userRecord = userRepository
        .findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found" + id));

    if (userDTO.getEmail() != null) {
      userRecord.setEmail(userDTO.getEmail());
    }

    if (userDTO.getName() != null) {
      userRecord.setName(userDTO.getName());
    }

    return userRepository.save(userRecord);
  }

  public void deleteUser(Long userId) {
    userRepository.deleteById(userId);
  }

  public User findUser(Long userId) throws UserNotFoundException {
    User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found" + userId));
    return user;
  }

}
