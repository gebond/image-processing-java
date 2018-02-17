package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.context.FourierContext;

/**
 * Created by Gleb on 27.01.2018.
 */
public abstract class Operation1D implements Operation<FourierContext.FourierContext1D> {
    @Override
    public void validate(FourierContext.FourierContext1D context) throws IllegalArgumentException {
        if (context.getFourierData().getArray1DCopy() == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = context.getFourierData().getSize();
        if (len == 0) {
            throw new IllegalArgumentException("Input array size must be > 0");
        }
        if ((len & 1) != 0) {
            throw new IllegalArgumentException("Input array size must be 2^k , k~Z");
        }
    }
}
