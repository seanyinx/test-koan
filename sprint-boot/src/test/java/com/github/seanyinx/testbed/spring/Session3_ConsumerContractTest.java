package com.github.seanyinx.testbed.spring;

import static com.seanyinx.github.unit.scaffolding.AssertUtils.expectFailing;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;

import au.com.dius.pact.consumer.ConsumerPactTestMk2;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

public class Session3_ConsumerContractTest extends ConsumerPactTestMk2 {

  @Override
  public RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", APPLICATION_JSON_VALUE);

    return pactDslWithProvider
        .given("User Sean exists")
          .uponReceiving("request for user Sean")
          .path("/users/1")
          .willRespondWith()
        .given("User Jack does not exist")
          .uponReceiving("request for user Jack")
          .method("GET")
          .path("/users/2")
          .willRespondWith()
        .toPact();
  }

  @Override
  public void runTest(MockServer mockServer) throws IOException {
    UserFetcher userFetcher = new UserFetcher(mockServer.getUrl());

    User user = userFetcher.fetch(1);

    assertThat(user.id()).isEqualTo(1L);
    assertThat(user.name()).isEqualTo("Sean");

    try {
      userFetcher.fetch(2);
      expectFailing(IllegalStateException.class);
    } catch (Exception ignored) {
    }
  }

  @Override
  protected String providerName() {
    return "user-service";
  }

  @Override
  protected String consumerName() {
    return "user-fetcher";
  }
}