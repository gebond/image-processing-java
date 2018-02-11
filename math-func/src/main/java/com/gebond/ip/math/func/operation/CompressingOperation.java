package com.gebond.ip.math.func.operation;

/**
 * Created on 11/02/18.
 */
public abstract class CompressingOperation<T extends FourierContext> implements Operation<T> {

    @Override
    public void validate(T context) throws IllegalArgumentException {
        if (context.getFourierData() == null) {
            throw new IllegalArgumentException("FourierData must be set to proceed.");
        }
        if (context.getCompressionSetting() == null) {
            throw new IllegalArgumentException("CompressionSetting must be set to proceed.");
        }
    }
}
