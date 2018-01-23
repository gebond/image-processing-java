package com.gebond.ip.math.func.transform;

/**
 * Created by Gleb on 17.10.2017.
 */
public class FourierData {

    private double[][] array2D;
    private double[] array1D;
    private final Dimension dimension;
    private final int size;

    public FourierData(double[] array1D) {
        this.dimension = Dimension.DIMENSION_1D;
        this.size = array1D.length;
        this.array1D = array1D;
    }

    public FourierData(double[][] array2D) {
        this.dimension = Dimension.DIMENSION_2D;
        this.size = array2D.length;
        this.array2D = array2D;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public int getSize() {
        return size;
    }

    public double[][] getArray2D() {
        return array2D;
    }

    public void setArray2D(double[][] array2D) {
        this.array2D = array2D;
    }

    public double[] getArray1D() {
        return array1D;
    }

    public void setArray1D(double[] array1D) {
        this.array1D = array1D;
    }

    public enum Dimension{
        DIMENSION_1D,
        DIMENSION_2D
    }
}
