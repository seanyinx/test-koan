package com.github.seanyinx.testbed.spring;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserService;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;

@RunWith(PactRunner.class)
@Provider("user-service")
@PactFolder("target/pacts")
public class Session4_ProviderContractTest {

  private static ConfigurableApplicationContext context;
  private static UserService userService;

  @TestTarget
  public final Target target = new HttpTarget(8081);

  @BeforeClass
  public static void startCustomerService() {
    context = SpringApplication
        .run(UserApplication.class, "--server.port=8081", "--spring.main.web-environment=true");

    userService = context.getBean(UserService.class);
  }

  @AfterClass
  public static void tearDown() throws Exception {
    context.close();
  }

  @State("User Sean exists")
  public void returnExistingUser() {
    when(userService.findUser(1L)).thenReturn(Optional.of(new User(1L, "Sean")));
  }

  @State("User Jack does not exist")
  public void rejectUnknownUser() {
    when(userService.findUser(2L)).thenReturn(Optional.empty());
  }

  @SpringBootApplication
  static class UserApplication {

    public static void main(String[] args) {
      SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    UserService userService() {
      return mock(UserService.class);
    }
  }
}