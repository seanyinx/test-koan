package com.github.seanyinx.testbed.spring;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.seanyinx.testbed.base.services.UserService;
import com.github.seanyinx.testbed.spring.domain.UserEntity;
import com.github.seanyinx.testbed.spring.infrastructure.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Session2_DatabaseTest {
  @Autowired
  private UserRepo userRepo;

  @MockBean
  private UserService userService;


  @Test
  public void shouldFindUserById() throws Exception {
    Optional<UserEntity> user = userRepo.findById(1L);

    assertThat(user).isPresent();
  }

  @Test
  public void shouldDeleteUserById() throws Exception {
    userRepo.deleteById(1L);

    Optional<UserEntity> user = userRepo.findById(1L);

    assertThat(user).isNotPresent();
  }

  @Test
  public void shouldUpdateUserById() throws Exception {
    userRepo.save(new UserEntity(1L, "mike"));

    Optional<UserEntity> user = userRepo.findById(1L);

    assertThat(user).isPresent();
    assertThat(user.get().getId()).isOne();
    assertThat(user.get().getName()).isEqualTo("mike");
  }
}
