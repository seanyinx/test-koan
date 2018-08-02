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

    assertThat(a).isEqualTo("abc");
  }

  @Test
  public void shouldHaveSize() {
    String a = "abc";

    assertThat(a).hasSize(3);
  }

  @Test
  public void shouldStartWith() {
    String a = uniquify("abc");

    assertThat(a).startsWith("abc");
  }

  @Test
  public void shouldContain() {
    String a = uniquify("") + uniquify("abc");

    assertThat(a).contains("abc");
  }
}
