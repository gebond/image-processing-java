package com.gebond.ip.math.func.operation;

import javax.xml.bind.ValidationException;

/**
 * Created by Gleb on 27.01.2018.
 */
public abstract class Operation2D implements Operation<FourierContext.FourierContext2D> {
    @Override
    public void validate(FourierContext.FourierContext2D context) throws ValidationException {
        double[][] array2D = context.getFourierData().getArray2D();
        if (array2D == null) {
            throw new ValidationException("input array is null");
        }
        int row = array2D.length;
        if (row == 0) {
            throw new ValidationException("input array row size must be > 0");
        }
        int col = array2D[0].length;
        if (col == 0) {
            throw new ValidationException("input array col size must be > 0");
        }
        if (row != col) {
            throw new ValidationException("input array col size must equal row size");
        }
        if ((row & 1) != 0 || (col & 1) != 0) {
            throw new ValidationException(
                    "input array size must be 2^k , k~Z Actual row,col = " + row + "," + col);
        }
    }
}
