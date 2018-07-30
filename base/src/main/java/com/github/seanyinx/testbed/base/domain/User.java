package com.github.seanyinx.testbed.base.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

  private final String name;
  private long id;

  public User(String name) {
    this.name = name;
  }

  @JsonCreator
  public User(@JsonProperty("id") long id, @JsonProperty("name") String name) {
    this.name = name;
    this.id = id;
  }

  public long id() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String name() {
    return name;
  }
}
