package com.gebond.ip.math.commons.util;

import java.util.Arrays;

/**
 * Created by Gleb on 22.10.2017.
 */
public class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * produces NPE if source array is null, or sub-array is null
     */
    public static double[][] arrayCopy(double[][] source) {
        double[][] target = new double[source.length][source[0].length];
        for (int i = 0; i < source.length; i++) {
            target[i] = Arrays.copyOf(source[i], source[i].length);
        }
        return target;
    }
}
