package com.github.seanyinx.testbed.base;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class Session5_MockRemoteTest {

  @ClassRule
  public static final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @BeforeClass
  public static void setUp() {
    // TODO: mock response of remote service
    stubFor(get(urlEqualTo("/users/1")));
  }

  @Test
  public void shouldAdaptJsonToUser() {
    // TODO: mock response of remote service
    UserFetcher fetcher = new UserFetcher("http://localhost:" + wireMockRule.port());

    User user = fetcher.fetch(1L);

    assertThat(user.id()).isOne();
    assertThat(user.name()).isEqualTo("jacky");
  }
}
