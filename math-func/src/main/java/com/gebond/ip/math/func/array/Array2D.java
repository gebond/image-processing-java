package com.gebond.ip.math.func.array;

import com.gebond.ip.math.commons.util.ArrayUtil;

import static com.gebond.ip.math.commons.util.ArrayUtil.copyOf;

/**
 * Created on 10/02/18.
 */
public class Array2D extends ArrayContainer {

    private double[][] array2D;

    public Array2D(double[][] array) {
        setArray(array);
    }

    public double[][] getArray2DCopy() {
        return ArrayUtil.copyOf(array2D);
    }

    @Override
    public int getSize() {
        return array2D.length * array2D[0].length;
    }

    @Override
    public Object getArrayCopy() {
        return getArray2DCopy();
    }

    @Override
    protected void setArrayAsCopyOf(Object array) {
        array2D = copyOf((double[][]) array);
    }
}
