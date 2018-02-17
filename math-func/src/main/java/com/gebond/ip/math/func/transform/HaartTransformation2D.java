package com.gebond.ip.math.func.transform;

import com.gebond.ip.math.func.compression.CompressionOperation2D;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.Operation2D;
import com.gebond.ip.math.func.operation.OperationManager;

import java.util.Arrays;
import java.util.List;

import static com.gebond.ip.math.func.transform.FourierTransform.HaartFourierTransform.doAnalysis;
import static com.gebond.ip.math.func.transform.FourierTransform.HaartFourierTransform.doSynthesis;

/**
 * Created by Gleb on 21.01.2018.
 */
public class HaartTransformation2D extends OperationManager<FourierContext.FourierContext2D> {
    @Override
    public List<Operation<FourierContext.FourierContext2D>> getOperations() {
        return Arrays.asList(
                new HaartAnalysis2D(),
                new CompressionOperation2D(),
                new HaartSynthesis2D());
    }

    public static class HaartAnalysis2D extends Operation2D {
        @Override
        public void apply(FourierContext.FourierContext2D context) {
            double[][] input = context.getFourierData().getArray2DCopy();
            int length = input.length;
            // 1 step: applying transformation to rows
            for (int i = 0; i < length; i++) {
                input[i] = doAnalysis(input[i]);
            }

            double[] unit = new double[length];
            // 2nd step: applying transformation to cols
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < length; i++) {
                    unit[i] = input[i][j];
                }
                unit = doAnalysis(unit);
                for (int i = 0; i < length; i++) {
                    input[i][j] = unit[i];
                }
            }
            context.getFourierData().setArray(input);
        }
    }

    public static class HaartSynthesis2D extends Operation2D {
        @Override
        public void apply(FourierContext.FourierContext2D context) {
            double[][] input = context.getFourierData().getArray2DCopy();
            int length = input.length;
            double[] unit = new double[length];
            // 1st step: applying transformation to cols
            for (int j = 0; j < length; j++) {
                for (int i = 0; i < length; i++) {
                    unit[i] = input[i][j];
                }
                unit = doSynthesis(unit);
                for (int i = 0; i < length; i++) {
                    input[i][j] = unit[i];
                }
            }
            // 2nd step: applying transformation to rows
            for (int i = 0; i < length; i++) {
                input[i] = doSynthesis(input[i]);
            }
            context.getFourierData().setArray(input);
        }
    }
}
