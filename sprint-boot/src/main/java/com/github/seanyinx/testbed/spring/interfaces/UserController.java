package com.github.seanyinx.testbed.spring.interfaces;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  User getUser(@PathVariable long id) {
    Optional<User> user = userService.findUser(id);

    return user.orElseThrow(() -> new NoSuchElementException("No such user with id " + id));
  }
}
