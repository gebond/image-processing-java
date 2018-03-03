package com.gebond.ip.math.func.compression;

import com.gebond.ip.math.func.context.FourierContext;

import java.util.ArrayList;
import java.util.List;

import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;
import static org.apache.commons.math3.util.FastMath.abs;

/**
 * Created on 10/02/18.
 */
public class CompressionOperation1D extends CompressingOperation<FourierContext.FourierContext1D> {
    @Override
    public void apply(FourierContext.FourierContext1D context) {
        if (context.getCompressionSetting().getCompressionRate() == MIN_COMPRESSION_RATE) {
            return; // no compression is required
        }
        double[] target = context.getFourierData().getArray1DCopy();
        List<Value> values = new ArrayList<>();
        for (int i = 0; i < target.length; i++) {
            values.add(new Value(i, target[i]));
        }

        values = sortAndSetZero(values, target.length, context);

        for (Value value : values) {
            target[value.i] = value.value;
        }
        context.getFourierData().setArray(target);
    }

    private class Value implements ValueGetterSetter {
        final int i;
        double value;

        public Value(int i, double value) {
            this.i = i;
            this.value = value;
        }

        @Override
        public double getValue() {
            return value;
        }

        @Override
        public void setValue(double val) {
            value = val;
        }

        @Override
        public int compareTo(Object o) {
            Value valObj = (Value) o;
            if (abs(value) < abs(valObj.value)) {
                return -1;
            } else if (abs(value) > abs(valObj.value)) {
                return 1;
            }
            return 0;
        }
    }
}
