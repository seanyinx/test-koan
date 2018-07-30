package com.github.seanyinx.testbed.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import org.junit.Test;

public class Session1_PrimitiveTest {

  private final Random random = new Random();

  @Test
  public void shouldBeOne() {
    int a = 1;

    assertThat(a);
    assertThat(a);
  }

  @Test
  public void shouldBeGreaterThanOne() {
    int a = 1 + random.nextInt();

    assertThat(a);
    assertThat(a);
  }

  @Test
  public void shouldBeInRange() {
    int a = 1 + random.nextInt(100);

    assertThat(a);
  }

  @Test
  public void shouldBeCloseTo() {
    double a = 10.d / 3.d;

    assertThat(a);
  }
}
