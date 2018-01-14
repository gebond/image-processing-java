package com.gebond.ip.math.commons.util;

/**
 * Created by Gleb on 22.10.2017.
 */
public class ArrayUtil {

    private ArrayUtil() {
    }

    public static void arrayCopy(double[] source, double[] target){
        System.arraycopy(source, 0, target, 0, source.length);
    }

    public static void arrayCopy(double[][] source, double[][] target){
        System.arraycopy(source, 0, target, 0, source.length);
    }
}
