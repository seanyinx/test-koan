package com.github.seanyinx.testbed;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.ClassRule;
import org.junit.Test;

import com.github.seanyinx.testbed.containers.RedisContainer;

public class Session1_DockerBasedTest {
  @ClassRule
  public static final RedisContainer redis = new RedisContainer();

  @Test
  public void shouldFetchDataFromCache() throws Exception {
    RedisCache cache = new RedisCache(redis.getContainerIpAddress(), redis.getMappedPort(6379));

    cache.put("foo", "bar");
    String value = cache.get("foo");

    assertThat(value).isEqualTo("bar");
  }
}
