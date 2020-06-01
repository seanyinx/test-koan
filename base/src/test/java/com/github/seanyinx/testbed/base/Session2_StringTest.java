package com.github.seanyinx.testbed.base;

import static com.seanyinx.github.unit.scaffolding.Randomness.uniquify;
import static org.assertj.core.api.Assertions.assertThat;

import com.seanyinx.github.unit.scaffolding.Randomness;
import java.util.Random;
import org.junit.Test;

public class Session2_StringTest {

  @Test
  public void shouldBeEqual() {
    String a = "abc";

    // TODO: complete the assertion below: a is equal to "abc"
    assertThat(a);
  }

  @Test
  public void shouldHaveSize() {
    String a = "abc";

    // TODO: complete the assertion below: a has size 3
    assertThat(a);
  }

  @Test
  public void shouldStartWith() {
    String a = uniquify("abc");

    // TODO: complete the assertion below: a starts with "abc"
    assertThat(a);
  }

  @Test
  public void shouldContain() {
    String a = uniquify("") + uniquify("abc");

    // TODO: complete the assertion below: a contains "abc"
    assertThat(a);
  }
}
