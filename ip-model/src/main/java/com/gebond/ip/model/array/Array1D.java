package com.gebond.ip.model.array;

import java.util.Arrays;

/**
 * Created on 10/02/18.
 */
public class Array1D extends ArrayContainer {

    private double[] array1D;

    public Array1D(double[] array) {
        setArray(array);
    }

    public double[] getArray1DCopy() {
        return Arrays.copyOf(array1D, array1D.length);
    }

    @Override
    public int getSize() {
        return array1D.length;
    }

    @Override
    public Object getArrayCopy() {
        return getArray1DCopy();
    }

    @Override
    protected void setArrayAsCopyOf(Object array) {
        array1D = Arrays.copyOf((double[]) array, ((double[]) array).length);
    }
}
