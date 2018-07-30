package com.github.seanyinx.testbed.base.domain;

import java.util.Optional;

public interface UserRepository {

  Optional<User> findOne(long userId);

  Optional<User> findOne(String name);

  void save(User user);
}
