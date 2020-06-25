package com.github.seanyinx.testbed.spring;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.rcplatform.athena.platform.test.ContractBroker;
import com.rcplatform.athena.platform.test.ContractRunner;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ContractRunner.class)
public class Session3_ConsumerContractTest {

  @ContractBroker(providerName = "user-service", consumerName = "user-fetcher")
  @ClassRule
  public static final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @Test
  public void userSeanExists() {
    stubFor(get(urlEqualTo("/users/1"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody("{\n"
                + "  \"id\": 1,\n"
                + "  \"name\": \"Sean\"\n"
                + "}")
        ));

    UserFetcher userFetcher = new UserFetcher("http://localhost:" + wireMockRule.port());

    User user = userFetcher.fetch(1);

    assertThat(user.id()).isEqualTo(1L);
    assertThat(user.name()).isEqualTo("Sean");
  }

  @Test(expected = IllegalStateException.class)
  public void userJackMissing() {
    stubFor(get(urlEqualTo("/users/2"))
        .willReturn(aResponse()
            .withStatus(500)));

    UserFetcher userFetcher = new UserFetcher("http://localhost:" + wireMockRule.port());
    userFetcher.fetch(2);
  }
}
