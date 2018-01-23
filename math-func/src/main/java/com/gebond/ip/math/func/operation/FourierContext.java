package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.transform.FourierData;

/**
 * Created by Gleb on 18.10.2017.
 */
public class FourierContext extends OperationContext {
    private FourierData fourierData;

    private FourierContext() {

    }

    public static FourierContextBuilder startBuild() {
        return new FourierContextBuilder();
    }

    public FourierData getFourierData() {
        return fourierData;
    }

    public static class FourierContextBuilder {
        private FourierContext fourierContext = new FourierContext();

        public FourierContextBuilder withArray(double[] array1D) {
            fourierContext.fourierData = new FourierData(array1D);
            return this;
        }

        public FourierContextBuilder withArray(double[][] array2D) {
            fourierContext.fourierData = new FourierData(array2D);
            return this;
        }

        public FourierContext build() {
            return fourierContext;
        }
    }
}
