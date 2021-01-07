package br.cefetmg.address.utils;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class UtilsTest {

  @Test
  void stringOr() {
    Assertions.assertEquals(Utils.stringOr("", "a"), "a", "stringOr(, a)");
    Assertions.assertEquals(Utils.stringOr("a", "b"), "a", "stringOr(a, b)");
    Assertions.assertEquals(Utils.stringOr("", ""), "", "Empty stringOr");
  }
}
