package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;
import static com.gebond.ip.math.commons.util.MathUtil.getCenters;
import static com.gebond.ip.math.func.util.Functions.walsh;

/**
 * Created by Gleb on 08.11.2017.
 */
public class WalshFourierTransformation1D extends FourierTransformation {

    @Override
    void analysis1D(FourierData fourierData) {
        int len = fourierData.getSize();
        double[] input = fourierData.getArray1D();

        double[] target = new double[len];
        calculatingValues(input, target, 0);
        fourierData.setArray1D(target);
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
                double walsh = walsh(j, x[i]);
                fun_ith += array[j] * walsh;
            }
            fun_values[i] = fun_ith;
        }
        fourierData.setArray1D(fun_values);
    }

    private void calculatingValues(double[] input, double[] target, int current) {
        int len = input.length;
        if(len == 1) {
            target[current] = input[0];
            return; // the end of recursion
        }
        int N = len / 2;
        double[] copy = new double[len];
        arrayCopy(input, copy);
        for(int j = 0; j < N; j++) {
            input[j] = 0.5 * ( copy[2 * j] + copy[2 * j + 1] );
            input[N + j] = 0.5 * ( copy[2 * j] - copy[2 * j + 1] );
        }
        double[] right = new double[N], left = new double[N];
        for(int j = 0; j < N; j++) {
            left[j] = input[j];
            right[j] = input[j + N];
        }
        // recursive calls for left and right parts
        calculatingValues(left, target, current); // left part of input array
        calculatingValues(right, target, current + N); // right part
    }
}
