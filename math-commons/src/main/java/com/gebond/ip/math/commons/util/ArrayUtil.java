package com.gebond.ip.math.commons.util;

import java.util.Arrays;

/**
 * Created by Gleb on 22.10.2017.
 */
public class ArrayUtil {

  private ArrayUtil() {
  }

  /**
   * produces exception if source array is null
   */
  public static double[] copyOf(double[] source) {
    if (source == null) {
      throw new IllegalArgumentException();
    }
    return Arrays.copyOf(source, source.length);
  }

  /**
   * produces exception if source array is null, or sub-array is null
   */
  public static double[][] copyOf(double[][] source) {
    if (source == null) {
      throw new IllegalArgumentException();
    }
    if (source.length == 0) {
      throw new IllegalArgumentException();
    }
    double[][] target = new double[source.length][source[0].length];
    for (int i = 0; i < source.length; i++) {
      target[i] = copyOf(source[i]);
    }
    return target;
  }
}
