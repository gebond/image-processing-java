package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.transform.FourierData;

/**
 * Created by Gleb on 18.10.2017.
 */
public abstract class FourierContext<T extends FourierData> extends OperationContext {

    public abstract T getFourierData();

    public static FourierContext1D fromArray(double[] array) {
        return new FourierContext1D(Dimension.DIMENSION_1D, array);
    }

    public static FourierContext2D fromArray(double[][] array) {
        return new FourierContext2D(Dimension.DIMENSION_2D, array);
    }

    protected Dimension dimension;
    protected T fourierData;

    public enum Dimension {
        DIMENSION_1D,
        DIMENSION_2D
    }

    public static class FourierContext1D extends FourierContext<FourierData.FourierData1D> {
        public FourierContext1D(Dimension dimension, double[] array) {
            this.dimension = dimension;
            this.fourierData = new FourierData.FourierData1D(array);
        }

        @Override
        public FourierData.FourierData1D getFourierData() {
            return super.fourierData;
        }
    }

    public static class FourierContext2D extends FourierContext<FourierData.FourierData2D> {
        public FourierContext2D(Dimension dimension, double[][] array) {
        }

        @Override
        public FourierData.FourierData2D getFourierData() {
            return super.fourierData;
        }
    }
}
