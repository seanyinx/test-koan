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
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MainApp.class)
//@AutoConfigureWireMock(port = 0)
public class Session3_ConsumerContractTest {

//  @MockBean
//  private UserService userService;

//  @Autowired
//  private Environment environment;

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
    wireMockRule.saveMappings();
  }

  @Test
  public void userSeanExists() {
    stubFor(get(urlEqualTo("/users/1"))
        .inScenario("User Sean exists")
        .withName("User Sean exists")
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
        .inScenario("User Jack does not exist")
        .withName("User Jack does not exist")
        .willReturn(aResponse()
            .withStatus(500)));

    UserFetcher userFetcher = new UserFetcher("http://localhost:" + wireMockRule.port());
    userFetcher.fetch(2);
  }
}
