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

    assertThat(alphabets).hasSize(3);
  }

  @Test
  public void shouldContainAll() {
    List<String> alphabets = asList("a", "b", "c");

    assertThat(alphabets).containsExactly("a", "b", "c");
  }

  @Test
  public void shouldContainInAnyOrder() {
    List<String> alphabets = new ArrayList<>(asList("a", "b", "c"));
    Collections.shuffle(alphabets);

    assertThat(alphabets).containsExactlyInAnyOrder("a", "b", "c");
  }

  @Test
  public void shouldHaveItem() {
    List<String> alphabets = new ArrayList<>(asList("a", "b", "c"));

    for (int i = 0; i < 10; i++) {
      if (random.nextInt() % 2 == 0) {
        alphabets.add(uniquify("blah"));
      }
    }

    assertThat(alphabets).contains("a", "b", "c");
  }
}
