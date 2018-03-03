package com.gebond.ip.math.func.compression;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.operation.Operation;

import java.util.List;

import static com.gebond.ip.model.setting.CompressionSetting.MAX_COMPRESSION_RATE;
import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;

/**
 * Created on 11/02/18.
 */
public abstract class CompressingOperation<T extends FourierContext> implements Operation<T> {

    @Override
    public boolean validate(T context) throws IllegalArgumentException {
        if (context.getFourierData() == null) {
            throw new IllegalArgumentException("FourierData must be set to proceed.");
        }
        if (context.getCompressionSetting() == null) {
            throw new IllegalArgumentException("CompressionSetting must be set to proceed.");
        }
        return true;
    }

    protected interface ValueGetterSetter extends Comparable {
        void setValue(double val);
    }

    protected <V extends ValueGetterSetter> List<V> sortAndSetZero(List<V> values, int size, T context) {
        int maxDeleteCounter = (int) (size *
                (MIN_COMPRESSION_RATE - context.getCompressionSetting().getCompressionRate()) /
                (MIN_COMPRESSION_RATE - MAX_COMPRESSION_RATE));
        values.sort((v1, v2) -> (v1.compareTo(v2)));
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
