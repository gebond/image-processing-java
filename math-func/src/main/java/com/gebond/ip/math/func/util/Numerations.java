package com.gebond.ip.math.func.util;

/**
 * Created by Gleb on 18.10.2017.
 */
public class Numerations {

  private Numerations() {
  }

  /**
   * @param n represents target number
   * @return array of {0, 1} in Paley format
   */
  public static int[] paley(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("n < 0 !");
    }
    String binary = Integer.toBinaryString(n);
    int[] result = new int[binary.length()];
    for (int i = 0; i < binary.length(); i++) {
      result[result.length - i - 1] = ((binary.charAt(i)) == '0') ? 0 : 1;
    }
    return result;
  }
}
