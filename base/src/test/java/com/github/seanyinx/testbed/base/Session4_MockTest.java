package com.github.seanyinx.testbed.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.domain.UserRepository;
import com.github.seanyinx.testbed.base.services.UserService;

public class Session4_MockTest {

  private final UserRepository userRepository = Mockito.mock(UserRepository.class);
  private final User user = new User("jack");

  @Test
  public void shouldFindUser() {
    when(userRepository.findOne(0L)).thenReturn(Optional.of(user));

    UserService userService = new UserService(userRepository);

    Optional<User> user = userService.findUser(0L);

    assertThat(user).isPresent();
    assertThat(user).contains(this.user);
  }

  @Test
  public void shouldSaveUser() {
    doAnswer(invocationOnMock -> {
      user.setId(1L);
      return null;
    }).when(userRepository).save(user);

    UserService userService = new UserService(userRepository);

    long userId = userService.save(user);

    assertThat(userId).isOne();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldSaveUserOnceOnly() {
    when(userRepository.findOne(user.name())).thenReturn(Optional.empty(), Optional.of(user));
    doAnswer(invocationOnMock -> {
      user.setId(1L);
      return null;
    }).when(userRepository).save(user);

    UserService userService = new UserService(userRepository);

    userService.save(user);
    long userId = userService.save(user);

    assertThat(userId).isOne();
    verify(userRepository, times(1)).save(user);
  }

  @Test (expected = IllegalStateException.class)
  public void shouldThrowIfDatabaseIsDown() {
    when(userRepository.findOne(0L)).thenThrow(new IllegalStateException("oops"));

    UserService userService = new UserService(userRepository);

    userService.findUser(0L);
  }
}
