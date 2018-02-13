package com.gebond.ip.math.func.transform;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.Operation2D;
import com.gebond.ip.math.func.operation.OperationManager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Gleb on 21.01.2018.
 */
public class HaartTransformation2D extends OperationManager<FourierContext.FourierContext2D> {
    @Override
    public List<Operation<FourierContext.FourierContext2D>> getOperations() {
        return Arrays.asList(
                new HaartAnalysis2D(),
                new HaartSynthesis2D());
    }

    public static class HaartAnalysis2D extends Operation2D {
        @Override
        public void apply(FourierContext.FourierContext2D context) {

        }
    }

    public static class HaartSynthesis2D extends Operation2D {
        @Override
        public void apply(FourierContext.FourierContext2D context) {

        }
    }
}
