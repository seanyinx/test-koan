package com.github.seanyinx.testbed.spring;

import static com.seanyinx.github.unit.scaffolding.AssertUtils.expectFailing;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.seanyinx.testbed.base.domain.User;
import com.github.seanyinx.testbed.base.services.UserFetcher;

import au.com.dius.pact.consumer.ConsumerPactTestMk2;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

public class Session3_ConsumerContractTest extends ConsumerPactTestMk2 {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
    Map<String, String> headers = new HashMap<>();
    headers.put("Content-Type", APPLICATION_JSON_VALUE);

    try {
      return pactDslWithProvider
          .given("User Sean exists")
            .uponReceiving("request for user Sean")
            .method("GET")
            .path("/users/1")
            .willRespondWith()
            .headers(headers)
            .status(HttpStatus.OK.value())
            .body(objectMapper.writeValueAsString(new User(1L, "Sean")), APPLICATION_JSON)
          .given("User Jack does not exist")
            .uponReceiving("request for user Jack")
            .method("GET")
            .path("/users/2")
            .willRespondWith()
            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
          .toPact();
    } catch (JsonProcessingException e) {
      throw new IllegalStateException(e);
    }
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