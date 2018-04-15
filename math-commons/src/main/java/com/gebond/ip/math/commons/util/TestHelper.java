package com.gebond.ip.math.commons.util;

import static com.gebond.ip.math.commons.util.MathUtil.intPow;

import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Assertions;

/**
 * Created on 17/02/18.
 */
public final class TestHelper {

  public static final double DELTA = 0.0000000000001f;

  private TestHelper() {
  }

  public static void assertArrayEqualsWithDelta(double[][] expected, double[][] actual) {
    for (int i = 0; i < expected.length; i++) {
      Assertions.assertArrayEquals(expected[i], actual[i], DELTA);
    }
  }

  public static void assertArrayEqualsWithDelta(double[][] expected, double[][] actual,
      double delta) {
    for (int i = 0; i < expected.length; i++) {
      Assertions.assertArrayEquals(expected[i], actual[i], delta);
    }
  }

  /**
   * random doubles uses random array length
   */
  public static double[] randomDoubles() {
    return randomDoubles(ThreadLocalRandom.current().nextInt(2, 5));
  }

  /**
   * random doubles uses provided array length
   */
  public static double[] randomDoubles(int len) {
    double[] result = new double[intPow(2, len)];
    for (int i = 0; i < result.length; i++) {
      result[i] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
    }
    return result;
  }

  /**
   * random doubles uses random array length
   */
  public static double[][] randomDoubles2D() {
    return randomDoubles2D(ThreadLocalRandom.current().nextInt(2, 5));
  }

  /**
   * random doubles uses provided array length
   */
  public static double[][] randomDoubles2D(int len) {
    double[][] result = new double[intPow(2, len)][intPow(2, len)];
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result.length; j++) {
        result[i][j] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
      }
    }
    return result;
  }
}
