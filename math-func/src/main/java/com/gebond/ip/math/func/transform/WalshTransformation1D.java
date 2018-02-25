package com.gebond.ip.math.func.transform;

import com.gebond.ip.math.func.compression.CompressionOperation1D;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.Operation1D;
import com.gebond.ip.math.func.operation.OperationManager;

import java.util.Arrays;
import java.util.List;

import static com.gebond.ip.math.func.transform.FourierTransform.WalshFourierTransform.doAnalysis;
import static com.gebond.ip.math.func.transform.FourierTransform.WalshFourierTransform.doSynthesis;


/**
 * Created by Gleb on 21.01.2018.
 */
public class WalshTransformation1D extends OperationManager<FourierContext.FourierContext1D> {
    @Override
    public List<Operation<FourierContext.FourierContext1D>> getOperations() {
        return Arrays.asList(
                new WalshAnalysis1D(),
                new CompressionOperation1D(),
                new WalshSynthesis1D());
    }

    public static class WalshAnalysis1D extends Operation1D {
        @Override
        public void apply(FourierContext.FourierContext1D context) {
            double[] result = doAnalysis(context.getFourierData().getArray1DCopy());
            context.getFourierData().setArray(result);
        }
    }

    public static class WalshSynthesis1D extends Operation1D {
        @Override
        public void apply(FourierContext.FourierContext1D context) {
            double[] result = doSynthesis(context.getFourierData().getArray1DCopy());
            context.getFourierData().setArray(result);
        }
    }
}
