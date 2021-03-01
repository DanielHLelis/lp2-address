package br.cefetmg.address.utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * utilities zeroton class
 */
public class Utils {

  private Utils() {
  }

  /**
   * Somewhat replicates the or operation between 2 strings present in other programming languages
   *
   * @param a
   * @param b
   * @return a || b
   */
  public static String stringOr(Object a, Object b) {
    if (a == null || a.toString().replaceAll("\\s", "").equals("")) {
      if (b == null) {
        return null;
      }
      return b.toString();
    }

    return a.toString();
  }
}
