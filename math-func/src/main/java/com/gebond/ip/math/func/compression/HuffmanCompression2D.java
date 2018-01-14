package com.gebond.ip.math.func.compression;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.math3.util.FastMath.abs;

/**
 * Created by Gleb on 17.10.2017.
 */
public class HuffmanCompression2D extends Compression<Array2DSetting> {

    @Override
    protected Array2DSetting doCompress(Array2DSetting compressionSetting) {
        double[][] target = compressionSetting.getTarget();

        int maxDeleteCounter = (int) (target.length * target[0].length * (1.0 - (compressionSetting.compressionRate / 100.0)));

        List<Value> values = new ArrayList<>();
        for (int i = 0; i < target.length; i++) {
            for (int j = 0; j < target.length; j++) {
                values.add(new Value(i, j, target[i][j]));
            }
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
            target[value.i][value.j] = value.value;
        }

        compressionSetting.setTarget(target);
        return compressionSetting;
    }

    private class Value {
        final int i;
        final int j;
        double value;

        public Value(int i, int j, double value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }
    }
}
