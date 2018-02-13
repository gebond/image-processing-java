package com.gebond.ip.math.func.compression;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.operation.Operation;

import java.util.List;

import static com.gebond.ip.math.func.compression.CompressionSetting.MAX_COMPRESSION_RATE;
import static com.gebond.ip.math.func.compression.CompressionSetting.MIN_COMPRESSION_RATE;
import static org.apache.commons.math3.util.FastMath.abs;

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

    protected interface ValueGetterSetter {
        double getValue();

        void setValue(double val);
    }

    protected <V extends ValueGetterSetter> List<V> sortAndSetZero(List<V> values, int size, T context) {
        int maxDeleteCounter = (int) (size *
                (MIN_COMPRESSION_RATE - context.getCompressionSetting().getCompressionRate()) /
                (MIN_COMPRESSION_RATE - MAX_COMPRESSION_RATE));
        values.sort((v1, v2) -> (abs(v1.getValue()) < abs(v2.getValue())) ? -1 : 1);
        int counter = 0;
        for (ValueGetterSetter value : values) {
            if (counter < maxDeleteCounter) {
                value.setValue(0.0);
                counter++;
            } else {
                break;
            }
        }
        return values;
    }

}
