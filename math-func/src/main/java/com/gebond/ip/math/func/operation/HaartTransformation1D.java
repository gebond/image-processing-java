package com.gebond.ip.math.func.operation;

import java.util.Arrays;
import java.util.List;

import static com.gebond.ip.math.commons.util.MathUtil.getCenters;
import static com.gebond.ip.math.commons.util.MathUtil.intPow;
import static com.gebond.ip.math.func.transform.FourierData.Dimension.DIMENSION_1D;
import static com.gebond.ip.math.func.util.Functions.haart;
import static java.lang.System.arraycopy;
import static org.apache.commons.math3.util.FastMath.log;

/**
 * Created by Gleb on 21.01.2018.
 */
public class HaartTransformation1D extends OperationManager<FourierContext> {
    @Override
    List<Operation<FourierContext>> getOperations() {
        return Arrays.asList(
                new HaartAnalysis1D(),
                new HaartSynthesis1D());
    }

    public static class HaartAnalysis1D implements Operation<FourierContext> {

        @Override
        public boolean isValid(FourierContext context) {
            return context.getFourierData().getDimension().equals(DIMENSION_1D);
        }

        @Override
        public void apply(FourierContext context) {
            int len = context.getFourierData().getSize();
            double[] input = context.getFourierData().getArray1D();

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
    }

    public static class HaartSynthesis1D implements Operation<FourierContext> {

        @Override
        public boolean isValid(FourierContext context) {
            return context.getFourierData().getDimension().equals(DIMENSION_1D);
        }

        @Override
        public void apply(FourierContext context) {
            int n = context.getFourierData().getSize();
            double[] array = context.getFourierData().getArray1D();
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
            context.getFourierData().setArray1D(fun_values);
        }
    }
}
