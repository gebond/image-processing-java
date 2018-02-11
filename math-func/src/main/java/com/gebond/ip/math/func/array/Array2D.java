package com.gebond.ip.math.func.array;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;

/**
 * Created on 10/02/18.
 */
public class Array2D extends ArrayContainer {

    private double[][] array2D;

    public Array2D(double[][] array) {
        setArray(array);
    }

    public double[][] getArray2D() {
        return (double[][]) getArray();
    }

    @Override
    public Object getArray() {
        return array2D;
    }

    @Override
    protected void setArrayAsCopyOf(Object array) {
        array2D = arrayCopy((double[][]) array);
    }
}
