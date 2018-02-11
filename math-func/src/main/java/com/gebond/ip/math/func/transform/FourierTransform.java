package com.gebond.ip.math.func.transform;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;

import static com.gebond.ip.math.commons.util.MathUtil.getCenters;
import static com.gebond.ip.math.commons.util.MathUtil.intPow;
import static com.gebond.ip.math.func.util.Functions.haart;
import static java.lang.System.arraycopy;
import static org.apache.commons.math3.util.FastMath.log;

/**
 * Created by Gleb on 28.01.2018.
 */
public class FourierTransform {

    private FourierTransform() {
    }

    public static class HaartFourierTransform {
        public static double[] doAnalysis(@NotNull double[] input) {
            int len = input.length;
            double[] result = Arrays.copyOf(input, len); // after copy no use of input of array

            int k = (int) log(2, len);
            while (k > 0) {
                double[] copy = new double[len];
                arraycopy(result, 0, copy, 0, len);
                for (int j = 0; j < intPow(2, k - 1); j++) {
                    result[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
                    result[intPow(2, k - 1) + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
                }
                k--;
            }
            return result;
        }

        public static double[] doSynthesis(@NotNull double[] input) {
            int len = input.length;
            double[] result = new double[len];
            double[] centers = getCenters(len);

            for (int i = 0; i < len; i++) {
                double fun_ith = 0.0;
                for (int j = 0; j < len; j++) {
                    double haart = haart(j, centers[i]);
                    fun_ith += input[j] * haart;
                }
                result[i] = fun_ith;
            }
            return result;
        }
    }

    public static class WalshFourierTransform {
        public static double[] doAnalysis(@NotNull double[] input) {
            int len = input.length;
            double[] result = Arrays.copyOf(input, len); // after copy no use of input of array

            int k = (int) log(2, len);
            while (k > 0) {
                double[] copy = new double[len];
                arraycopy(result, 0, copy, 0, len);
                for (int j = 0; j < intPow(2, k - 1); j++) {
                    result[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
                    result[intPow(2, k - 1) + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
                }
                k--;
            }
            return result;
        }

        public static double[] doSynthesis(@NotNull double[] input) {
            int len = input.length;
            double[] result = new double[len];
            double[] centers = getCenters(len);

            for (int i = 0; i < len; i++) {
                double fun_ith = 0.0;
                for (int j = 0; j < len; j++) {
                    double haart = haart(j, centers[i]);
                    fun_ith += input[j] * haart;
                }
                result[i] = fun_ith;
            }
            return result;
        }
    }
}
