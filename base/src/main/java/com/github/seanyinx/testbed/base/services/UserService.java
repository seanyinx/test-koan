package com.github.seanyinx.testbed.base.services;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.domain.UserRepository;
import java.util.Optional;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> findUser(long userId) {
    return userRepository.findOne(userId);
  }

  public long save(User user) {
    if (userRepository.findOne(user.name()).isPresent()) {
      return user.id();
    }

    userRepository.save(user);
    return user.id();
  }
}
