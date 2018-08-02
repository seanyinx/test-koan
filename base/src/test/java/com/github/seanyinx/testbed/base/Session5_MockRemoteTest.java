package com.github.seanyinx.testbed.base;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class Session5_MockRemoteTest {

  @ClassRule
  public static final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private static final User jacky = new User(1L, "jacky");

  @BeforeClass
  public static void setUp() throws JsonProcessingException {
    stubFor(get(urlEqualTo("/users/1"))
        .willReturn(
            aResponse()
                .withStatus(SC_OK)
                .withBody(objectMapper.writeValueAsString(jacky))));
  }

  @Test
  public void shouldAdaptJsonToUser() {
    UserFetcher fetcher = new UserFetcher("http://localhost:" + wireMockRule.port());

    User user = fetcher.fetch(1L);

    assertThat(user.id()).isOne();
    assertThat(user.name()).isEqualTo("jacky");
  }
}
