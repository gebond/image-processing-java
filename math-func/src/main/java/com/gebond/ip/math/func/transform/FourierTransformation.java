package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;

/**
 * Created by Gleb on 17.10.2017.
 */
public abstract class FourierTransformation {

    abstract void analysis1D(FourierData fourierData);

    abstract void synthesis1D(FourierData fourierData);

    public void analysis(FourierData fourierData) {
        validateData(fourierData);
        if (fourierData.getDimension() == 1) {
            analysis1D(fourierData);
            return;
        }
        if (fourierData.getDimension() == 2) {
            analysis2D(fourierData);
            return;
        }
    }

    public void synthesis(FourierData fourierData) {
        validateData(fourierData);
        if (fourierData.getDimension() == 1) {
            synthesis1D(fourierData);
            return;
        }
        if (fourierData.getDimension() == 2) {
            synthesis2D(fourierData);
            return;
        }
    }

    protected void analysis2D(FourierData fourierData) {
        double[][] input = fourierData.getArray2D();
        int rows = input.length;
        int cols = input[0].length;
        double[] unit = new double[cols];
        // 1 step: applying transformation to rows
        for (int i = 0; i < rows; i++) {
            arrayCopy(input[i], unit);
            fourierData.setArray1D(unit);
            analysis1D(fourierData);
            arrayCopy(fourierData.getArray1D(), input[i]);
        }
        // 2nd step: applying transformation to cols
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                unit[i] = input[i][j];
            }
            fourierData.setArray1D(unit);
            analysis1D(fourierData);
            unit = fourierData.getArray1D();
            for (int i = 0; i < cols; i++) {
                input[i][j] = unit[i];
            }
        }
    }

    protected void synthesis2D(FourierData fourierData) {
        double[][] input = fourierData.getArray2D();
        int rows = input.length;
        int cols = input[0].length;
        double[] unit = new double[rows];
        // 1st step: applying transformation to cols
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                unit[i] = input[i][j];
            }
            fourierData.setArray1D(unit);
            synthesis1D(fourierData);
            unit = fourierData.getArray1D();
            for (int i = 0; i < cols; i++) {
                input[i][j] = unit[i];
            }
        }
        // 2nd step: applying transformation to rows
        for (int i = 0; i < rows; i++) {
            arrayCopy(input[i], unit);
            fourierData.setArray1D(unit);
            synthesis1D(fourierData);
            arrayCopy(fourierData.getArray1D(), input[i]);
        }
    }

    protected void validateData(FourierData fourierData) {
        if (fourierData.getDimension() == 1) {
            validate1D(fourierData.getArray1D());
            return;
        }
        if (fourierData.getDimension() == 2) {
            validate2D(fourierData.getArray2D());
            return;
        } else {
            throw new IllegalArgumentException("incorrect dimension");
        }
    }

    protected void validate1D(double[] array1D) {
        if (array1D == null) {
            throw new IllegalArgumentException("input array is null");
        }
        int len = array1D.length;
        if (len == 0) {
            throw new IllegalArgumentException("input array size must be > 0");
        }
        if ((len & 1) != 0) {
            throw new IllegalArgumentException("input array size must be 2^k , k~Z");
        }
    }

    protected void validate2D(double[][] array2D) {
        if (array2D == null) {
            throw new IllegalArgumentException("input array is null");
        }
        int row = array2D.length;
        if (row == 0) {
            throw new IllegalArgumentException("input array row size must be > 0");
        }
        int col = array2D[0].length;
        if (col == 0) {
            throw new IllegalArgumentException("input array col size must be > 0");
        }
        if (row != col) {
            throw new IllegalArgumentException("input array col size must equal row size");
        }
        if ((row & 1) != 0 || (col & 1) != 0) {
            throw new IllegalArgumentException(
                    "input array size must be 2^k , k~Z Actual row,col = " + row + "," + col);
        }
    }
}
