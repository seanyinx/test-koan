package com.github.seanyinx.testbed.spring;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserService;
import com.github.seanyinx.testbed.spring.interfaces.UserController;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = MainApp.class)
public class Session1_ControllerTest {
  private final User jack = new User(1L, "jack");

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @Test
  public void shouldReturnExpectedUser() throws Exception {
    when(userService.findUser(1L)).thenReturn(Optional.of(jack));

    mockMvc.perform(get("/users/{id}", 1))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name", is("jack")));
  }

  @Test
  public void shouldReturn500() throws Exception {
    when(userService.findUser(1L)).thenReturn(Optional.empty());

    mockMvc.perform(get("/users/{id}", 1))
        .andExpect(status().isInternalServerError());
  }
}
