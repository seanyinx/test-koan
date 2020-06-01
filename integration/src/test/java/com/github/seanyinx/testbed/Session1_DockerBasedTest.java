package com.github.seanyinx.testbed;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class Session1_DockerBasedTest {

  @Test
  public void shouldFetchDataFromCache() throws Exception {
    // TODO: make test pass with RedisContainer
    RedisCache cache = new RedisCache("localhost", 6379);

    cache.put("foo", "bar");
    String value = cache.get("foo");

    assertThat(value).isEqualTo("bar");
  }
}
