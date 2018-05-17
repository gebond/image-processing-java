package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.commons.util.ArrayUtil.copyOf;
import static com.gebond.ip.math.commons.util.MathUtil.getCenters;
import static com.gebond.ip.math.commons.util.MathUtil.intPow;
import static com.gebond.ip.math.func.util.Functions.haart;
import static com.gebond.ip.math.func.util.Functions.walsh;
import static org.apache.commons.math3.util.FastMath.PI;
import static org.apache.commons.math3.util.FastMath.cos;
import static org.apache.commons.math3.util.FastMath.log;
import static org.apache.commons.math3.util.FastMath.pow;

import com.gebond.ip.model.array.Vector;
import com.gebond.ip.model.array.Vector2D;
import com.gebond.ip.model.array.Vector3D;
import com.gebond.ip.model.setting.GFieldCacheUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gleb on 28.01.2018.
 */
public class FourierTransform {

  private FourierTransform() {
  }

  public static class HaartFourierTransform {

    public static double[] doAnalysis(double[] input) {
      double[] result = copyOf(input); // after copy no use of input of array
      int len = result.length;

      int k = (int) log(2, len);
      while (k > 0) {
        double[] copy = copyOf(result);
        for (int j = 0; j < intPow(2, k - 1); j++) {
          result[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
          result[intPow(2, k - 1) + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
        }
        k--;
      }
      return result;
    }

    public static double[] doSynthesis(double[] input) {
      double[] inputCopy = copyOf(input); // after copy no use of input of array
      int len = inputCopy.length;

      double[] result = new double[len];
      double[] centers = getCenters(len);

      for (int i = 0; i < len; i++) {
        double fun_ith = 0.0;
        for (int j = 0; j < len; j++) {
          double haart = haart(j, centers[i]);
          fun_ith += inputCopy[j] * haart;
        }
        result[i] = fun_ith;
      }
      return result;
    }
  }

  public static class WalshFourierTransform {

    public static double[] doAnalysis(double[] input) {
      double[] inputCopy = copyOf(input); // after copy no use of input of array

      double[] target = new double[inputCopy.length];
      analysisIntoTarget(inputCopy, target, 0);

      return target;
    }

    public static double[] doSynthesis(double[] input) {
      double[] inputCopy = copyOf(input);
      int len = input.length;

      double[] result = new double[len];
      double[] centers = getCenters(len);

      for (int i = 0; i < len; i++) {
        double fun_ith = 0.0;
        for (int j = 0; j < len; j++) {
          double haart = walsh(j, centers[i]);
          fun_ith += inputCopy[j] * haart;
        }
        result[i] = fun_ith;
      }
      return result;
    }

    private static void analysisIntoTarget(double[] input, double[] target, int current) {
      int len = input.length;
      if (len == 1) {
        target[current] = input[0];
        return; // the end of recursion
      }
      int N = len / 2;
      double[] copy = copyOf(input);
      for (int j = 0; j < N; j++) {
        input[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
        input[N + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
      }
      double[] right = new double[N], left = new double[N];
      for (int j = 0; j < N; j++) {
        left[j] = input[j];
        right[j] = input[j + N];
      }
      // recursive calls for left and right parts
      analysisIntoTarget(left, target, current); // left part of input array
      analysisIntoTarget(right, target, current + N); // right part
    }
  }


  public static class DiscreteFourierTransform {

    public static double[] doAnalysis(Vector3D<Integer> params, double[] input) {
      int p = params.getX();
      int s = params.getY();
      int N = params.getZ();
      Vector2D<Integer> paramsPS = new Vector2D<>(p, s);
      double[] target = copyOf(input);

      for (int k = 0; k < N; k++) {
        double[] inputCopy = copyOf(target);
        for (int i = 0; i < inputCopy.length; i++) {
          List<Vector<Integer>> alphaVectors = new ArrayList<>(GFieldCacheUtils.get(params, i));
          Vector<Integer> oldAlpha = alphaVectors.get(k);
          double summ = 0.0;
          for (Vector<Integer> alphaVector : GFieldCacheUtils.get(paramsPS).alphas) {
            alphaVectors.set(k, alphaVector);
            int numIndex = GFieldCacheUtils.convertToNumber(p, s, alphaVectors);
            summ +=
                cos(((2.0 * PI) / p)
                * pow(p, s / 2.0)
                * pow(p, -s / 2.0)
                * alphaVector.multiply(oldAlpha))
                * inputCopy[numIndex];
          }
          target[i] = summ;
        }
      }
      return target;
    }

    public static double[] doSynthesis(Vector3D<Integer> params, double[] input) {
      int p = params.getX();
      int s = params.getY();
      int N = params.getZ();
      Vector2D<Integer> paramsPS = new Vector2D<>(p, s);
      double[] target = copyOf(input);

      for (int k = 0; k < N; k++) {
        double[] inputCopy = copyOf(target);
        for (int i = 0; i < inputCopy.length; i++) {
          List<Vector<Integer>> alphaVectors = new ArrayList<>(GFieldCacheUtils.get(params, i));
          Vector<Integer> oldAlpha = alphaVectors.get(k);
          double summ = 0.0;
          for (Vector<Integer> alphaVector : GFieldCacheUtils.get(paramsPS).alphas) {
            alphaVectors.set(k, alphaVector);
            int numIndex = GFieldCacheUtils.convertToNumber(p, s, alphaVectors);
            summ +=
                cos(((-2.0 * PI) / p)
                * (alphaVector.multiply(oldAlpha)))
                * pow(p, -s)
                * inputCopy[numIndex];
          }
          target[i] = summ;
        }
      }
      return target;
    }

//    private static void analysisIntoTarget(double[] input, double[] target, int current) {
//      int len = input.length;
//      if (len == 1) {
//        target[current] = input[0];
//        return; // the end of recursion
//      }
//      int N = len / 2;
//      double[] copy = copyOf(input);
//      for (int j = 0; j < N; j++) {
//        input[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
//        input[N + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
//      }
//      double[] right = new double[N], left = new double[N];
//      for (int j = 0; j < N; j++) {
//        left[j] = input[j];
//        right[j] = input[j + N];
//      }
//      // recursive calls for left and right parts
//      analysisIntoTarget(left, target, current); // left part of input array
//      analysisIntoTarget(right, target, current + N); // right part
//    }
  }
}
