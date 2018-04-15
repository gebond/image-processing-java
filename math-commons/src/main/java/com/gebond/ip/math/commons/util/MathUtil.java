package com.gebond.ip.math.commons.util;

import static org.apache.commons.math3.util.FastMath.pow;
import static org.apache.commons.math3.util.FastMath.sqrt;

/**
 * Created by Gleb on 21.10.2017.
 */
public class MathUtil {

  private MathUtil() {
  }

  public static int intPow(double base, double degree) {
    return (int) pow(base, degree);
  }

  /**
   * returns segment n center values from of [1, 0]. for example for n = 1 returns {0.5} n = 2
   * returns {0.25, 0.75} n = 4 returns {0.125, 0.375, 0.625, 0.875}
   */
  public static double[] getCenters(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("inserted values of n must be > 0 found " + n);
    }
    double[] centers = new double[n];
    for (int k = 0; k < n; k++) {
      centers[k] = (2.0 * k + 1.0) / (2.0 * n);
    }
    return centers;
  }

  public static int[] divideNumber(int n) {
    if (n <= 8) {
      throw new IllegalArgumentException("n > 8 but found " + n);
    }
    if (n % 2 == 0) {
      return new int[]{2, n / 2};
    }
    // 1 step
    for (int k = 3; k <= intPow(n, 1.0 / 3.0); k++) {
      if (n % k == 0) {
        return new int[]{k, n / k};
      }
    }
    //2 step
    for (int k = 1; k <= intPow(n, 1.0 / 3.0); k++) {
      for (int d = 0; d <= (int) (pow(n, 1.0 / 6.0) / (4.0 * sqrt(k))); d++) {
        int A = ((int) sqrt(4 * k * n)) + d;
        double B = pow(pow(A, 2) - 4 * k * n, 0.5);
        if (B >= 0 && B - (int) B == 0) {
          // means B is full square
          int summ = A + (int) B;
          int diff = A - (int) B;
          if (n % summ == 0) {
            return new int[]{summ, n / summ};
          }
          if (n % diff == 0) {
            return new int[]{diff, n / diff};
          }
        }
      }
    }
    return new int[]{};
  }
}
