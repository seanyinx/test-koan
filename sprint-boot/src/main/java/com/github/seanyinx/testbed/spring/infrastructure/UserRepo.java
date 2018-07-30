package com.github.seanyinx.testbed.spring.infrastructure;

import org.springframework.data.repository.CrudRepository;

import com.github.seanyinx.testbed.spring.domain.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
}
