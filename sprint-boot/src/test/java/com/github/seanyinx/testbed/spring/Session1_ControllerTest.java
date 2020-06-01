package com.github.seanyinx.testbed.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.spring.interfaces.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class Session1_ControllerTest {
  private final User jack = new User(1L, "jack");

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnExpectedUser() throws Exception {

    // TODO: mock behavior of user service
    // TODO: check response status and response body
    mockMvc.perform(get("/users/{id}", 1));
  }

  @Test
  public void shouldReturn500() throws Exception {

    // TODO: mock behavior of user service
    // TODO: check response status and response body
    mockMvc.perform(get("/users/{id}", 1));
  }
}
