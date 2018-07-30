package com.github.seanyinx.testbed.base;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.domain.UserRepository;
import com.github.seanyinx.testbed.base.services.UserService;
import java.util.Optional;
import org.junit.Test;
import org.mockito.Mockito;

public class Session4_MockTest {

  private final UserRepository userRepository = Mockito.mock(UserRepository.class);
  private final User user = new User("jack");

  @Test
  public void shouldFindUser() {
    UserService userService = new UserService(userRepository);

    Optional<User> user = userService.findUser(0L);

    assertThat(user).isPresent();
    assertThat(user).contains(this.user);
  }

  @Test
  public void shouldSaveUser() {
    UserService userService = new UserService(userRepository);

    long userId = userService.save(user);

    assertThat(userId).isOne();
  }

  @Test
  public void shouldSaveUserOnceOnly() {
    UserService userService = new UserService(userRepository);

    userService.save(user);
    long userId = userService.save(user);

    assertThat(userId).isOne();
  }

  @Test (expected = IllegalStateException.class)
  public void shouldThrowIfDatabaseIsDown() {
    UserService userService = new UserService(userRepository);

    userService.findUser(0L);
  }
}
