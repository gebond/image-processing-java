package com.gebond.ip.math.func.compression;

/**
 * Created by Gleb on 22.10.2017.
 */
public class Array1DSetting extends CompressionSetting {

    private double[] target;

    Array1DSetting(double[] target, double rate) {
        super(rate);
        this.target = target;
    }

    public double[] getTarget() {
        return target;
    }

    public void setTarget(double[] target) {
        this.target = target;
    }
}
