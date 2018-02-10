package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;

/**
 * Created by Gleb on 17.10.2017.
 */
public abstract class FourierData {

    protected double[][] array2D;
    protected double[] array1D;
    private final int size;

    private FourierData(double[] array1D) {
        this.size = array1D.length;
        this.array1D = new double[size];
        arrayCopy(array1D, this.array1D);
    }

    private FourierData(double[][] array2D) {
        this.size = array2D.length;
        this.array2D = new double[size][array2D[1].length];
        arrayCopy(array2D, this.array2D);
    }

    public int getSize() {
        return size;
    }

    public static class FourierData1D extends FourierData{
        public FourierData1D(double[] array1D) {
            super(array1D);
        }

        public double[] getArray1D() {
            return array1D;
        }

        public void setArray1D(double[] array1D) {
            super.array1D = new double[array1D.length];
            arrayCopy(array1D, this.array1D);
        }
    }

    public static class FourierData2D extends FourierData{
        public FourierData2D(double[][] array2D) {
            super(array2D);
        }

        public double[][] getArray2D() {
            return array2D;
        }

        public void setArray2D(double[][] array2D) {
            super.array2D = array2D;
        }
    }
}
