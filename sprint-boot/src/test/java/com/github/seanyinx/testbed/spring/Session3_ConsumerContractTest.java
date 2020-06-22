package com.github.seanyinx.testbed.spring;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

import com.atlassian.ta.wiremockpactgenerator.WireMockPactGenerator;
import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;
import com.github.tomakehurst.wiremock.admin.model.ListStubMappingsResult;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

public class Session3_ConsumerContractTest {

  @ClassRule
  public static final WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());

  @BeforeClass
  public static void setUp() {
    wireMockRule.addMockServiceRequestListener(
        WireMockPactGenerator
            .builder("user-fetcher", "user-service")
            .build()
    );
  }

  @AfterClass
  public static void tearDown() throws Exception {
    // TODO: 2020/6/22 register all stub mapping with wiremock client with aop on WireMockRule
    ListStubMappingsResult x = wireMockRule.listAllStubMappings();
    System.out.println(x);
    wireMockRule.saveMappings();
  }

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
