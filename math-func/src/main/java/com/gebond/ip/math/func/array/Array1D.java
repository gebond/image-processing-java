package com.gebond.ip.math.func.array;

import java.util.Arrays;

/**
 * Created on 10/02/18.
 */
public class Array1D extends ArrayContainer {

    private double[] array1D;

    public Array1D(double[] array){
        setArray(array);
    }

    public double[] getArray1D() {
        return (double[]) getArray();
    }

    @Override
    public Object getArray() {
        return array1D;
    }

    @Override
    protected void setArrayAsCopyOf(Object array) {
        array1D = Arrays.copyOf((double[]) array, ((double[]) array).length);
    }
}
