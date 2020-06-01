package com.github.seanyinx.testbed.base;

import static com.seanyinx.github.unit.scaffolding.Randomness.uniquify;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class Session3_CollectionTest {

  private final Random random = new Random();

  @Test
  public void shouldHaveSize() {
    List<String> alphabets = asList("a", "b", "c");

    // TODO: complete the assertion below: alphabets have size 3
    assertThat(alphabets);
  }

  @Test
  public void shouldContainAll() {
    List<String> alphabets = asList("a", "b", "c");

    // TODO: complete the assertion below: alphabets contain "a", "b", "c"
    assertThat(alphabets);
  }

  @Test
  public void shouldContainInAnyOrder() {
    List<String> alphabets = new ArrayList<>(asList("a", "b", "c"));
    Collections.shuffle(alphabets);

    // TODO: complete the assertion below: alphabets contain "a", "b", "c" in any order
    assertThat(alphabets);
  }

  @Test
  public void shouldHaveItem() {
    List<String> alphabets = new ArrayList<>(asList("a", "b", "c"));

    for (int i = 0; i < 10; i++) {
      if (random.nextInt() % 2 == 0) {
        alphabets.add(uniquify("blah"));
      }
    }

    // TODO: complete the assertion below: alphabets have item "a", "b", and "c"
    assertThat(alphabets);
  }
}
