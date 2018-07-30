package com.github.seanyinx.testbed.spring.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserEntity {
  @Id
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
