package com.gebond.ip.math.func.context;

import com.gebond.ip.model.array.Array1D;
import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.ArrayContainer;
import com.gebond.ip.model.setting.CompressionSetting;

/**
 * Created by Gleb on 18.10.2017.
 */
public abstract class FourierContext<
        T extends ArrayContainer,
        C extends CompressionSetting> extends OperationContext {

    protected T fourierData;
    protected C compressionSetting;

    public abstract T getFourierData();

    public abstract C getCompressionSetting();

    public static FourierContext1D fromArray(double[] array) {
        return new FourierContext1D(array);
    }

    public static FourierContext2D fromArray(double[][] array) {
        return new FourierContext2D(array);
    }

    public static FourierContext1DBuilder start1DBuilder(double[] array) {
        return new FourierContext1DBuilder(array);
    }

    public static FourierContext2DBuilder start2DBuilder(double[][] array) {
        return new FourierContext2DBuilder(array);
    }

    public static class FourierContext1DBuilder {
        FourierContext1D fourierContext;

        public FourierContext1DBuilder(double[] array) {
            fourierContext = new FourierContext1D(array);
        }

        public FourierContext1DBuilder withCompression(CompressionSetting compression) {
            fourierContext.compressionSetting = compression;
            return this;
        }

        public FourierContext1D build() {
            return fourierContext;
        }
    }

    public static class FourierContext2DBuilder {
        FourierContext2D fourierContext;

        public FourierContext2DBuilder(double[][] array) {
            fourierContext = new FourierContext2D(array);
        }

        public FourierContext2DBuilder withCompression(CompressionSetting compression) {
            fourierContext.compressionSetting = compression;
            return this;
        }

        public FourierContext2D build() {
            return fourierContext;
        }
    }

    public static class FourierContext1D extends FourierContext<Array1D, CompressionSetting> {

        public FourierContext1D(double[] array) {
            this.fourierData = new Array1D(array);
        }

        @Override
        public Array1D getFourierData() {
            return super.fourierData;
        }

        @Override
        public CompressionSetting getCompressionSetting() {
            return super.compressionSetting;
        }
    }

    public static class FourierContext2D extends FourierContext<Array2D, CompressionSetting> {

        public FourierContext2D(double[][] array) {
            this.fourierData = new Array2D(array);
        }

        @Override
        public Array2D getFourierData() {
            return super.fourierData;
        }

        @Override
        public CompressionSetting getCompressionSetting() {
            return super.compressionSetting;
        }
    }
}
