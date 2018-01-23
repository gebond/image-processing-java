package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.commons.util.MathUtil.getCenters;
import static com.gebond.ip.math.commons.util.MathUtil.intPow;
import static com.gebond.ip.math.func.util.Functions.haart;
import static java.lang.System.arraycopy;
import static org.apache.commons.math3.util.FastMath.log;

/**
 * Created by Gleb on 21.10.2017.
 */
public class HaartFourierTransformation1D extends FourierTransformation {

    @Override
    void analysis1D(FourierData fourierData) {
        int len = fourierData.getSize();
        double[] input = fourierData.getArray1D();

        int k = (int) log(2, len);
        while (k > 0) {
            double[] copy = new double[len];
            arraycopy(input, 0, copy, 0, len);
            for (int j = 0; j < intPow(2, k - 1); j++) {
                input[j] = 0.5 * (copy[2 * j] + copy[2 * j + 1]);
                input[intPow(2, k - 1) + j] = 0.5 * (copy[2 * j] - copy[2 * j + 1]);
            }
            k--;
        }
    }

    @Override
    void synthesis1D(FourierData fourierData) {
        int n = fourierData.getSize();
        double[] array = fourierData.getArray1D();
        double[] fun_values = new double[n];
        double[] x = getCenters(n);

        for (int i = 0; i < n; i++) {
            double fun_ith = 0.0;
            for (int j = 0; j < n; j++) {
                double haart = haart(j, x[i]);
                fun_ith += array[j] * haart;
            }
            fun_values[i] = fun_ith;
        }
        fourierData.setArray1D(fun_values);
    }
}
