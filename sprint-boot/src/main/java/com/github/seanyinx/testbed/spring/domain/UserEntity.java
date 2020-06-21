package com.github.seanyinx.testbed.spring.domain;

public class UserEntity {
  private long id;

  private String name;

  private UserEntity() {
  }

  public UserEntity(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
