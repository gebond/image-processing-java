package com.gebond.ip.math.func.transform;

import java.util.Arrays;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;

/**
 * Created by Gleb on 17.10.2017.
 */
@Deprecated
public abstract class FourierData {

    // TODO replace hardcoded arrays with generic n-dim object
    protected double[][] array2D;
    protected double[] array1D;
    private int size;

    protected void setArray1DCopy(double[] array1D) {
        this.size = array1D.length;
        this.array1D = Arrays.copyOf(array1D, array1D.length);
        array2D = null;
    }

    protected void setArray2DCopy(double[][] array2D) {
        this.size = array2D.length;
        this.array2D = arrayCopy(array2D);
        array1D = null;
    }

    private FourierData(double[] array1D) {
        if (array1D == null) {
            throw new IllegalArgumentException("Array must be non null");
        }
        setArray1DCopy(array1D);
    }

    private FourierData(double[][] array2D) {
        if (array2D == null) {
            throw new IllegalArgumentException("Array must be non null");
        }
        setArray2DCopy(array2D);
    }

    public int getSize() {
        return size;
    }

    public static class FourierData1D extends FourierData {
        public FourierData1D(double[] array1D) {
            super(array1D);
        }

        public double[] getArray1D() {
            return array1D;
        }

        public void setArray1D(double[] array1D) {
            setArray1DCopy(array1D);
        }
    }

    public static class FourierData2D extends FourierData {
        public FourierData2D(double[][] array2D) {
            super(array2D);
        }

        public double[][] getArray2D() {
            return array2D;
        }

        public void setArray2D(double[][] array2D) {
            setArray2DCopy(array2D);
        }
    }
}
