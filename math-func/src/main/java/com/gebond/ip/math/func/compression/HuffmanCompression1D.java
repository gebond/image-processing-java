package com.gebond.ip.math.func.compression;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.math3.util.FastMath.abs;

/**
 * Created by Gleb on 17.10.2017.
 */
@Deprecated
public class HuffmanCompression1D extends Compression<Array1DSetting> {

    @Override
    protected Array1DSetting doCompress(Array1DSetting compressionSetting) {
        double[] target = compressionSetting.getTarget();

        int maxDeleteCounter = (int) (target.length * (1.0 - (compressionSetting.getCompressionRate() / 100.0)));

        List<Value> values = new ArrayList<>();
        for (int i = 0; i < target.length; i++) {
            values.add(new Value(i, target[i]));
        }

        values.sort((v1, v2) -> (abs(v1.value) < abs(v2.value)) ? -1 : 1);

        int counter = 0;

        for (Value value : values) {
            if (counter < maxDeleteCounter) {
                value.value = 0.0;
                counter++;
            } else {
                break;
            }
        }

        for (Value value : values) {
            target[value.i] = value.value;
        }

        compressionSetting.setTarget(target);
        return compressionSetting;
    }

    private class Value {
        final int i;
        double value;

        public Value(int i, double value) {
            this.i = i;
            this.value = value;
        }
    }
}
