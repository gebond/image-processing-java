package com.gebond.ip.math.func.util;

import java.util.concurrent.ThreadLocalRandom;

import static com.gebond.ip.math.commons.util.MathUtil.intPow;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Created on 17/02/18.
 */
public final class TestHelper {

    private TestHelper() {
    }

    public static final double DELTA = 0.00000000001;

    public static void assertArrayEqualsWithDelta(double[][] expected, double[][] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], DELTA);
        }
    }

    public static void assertArrayEqualsWithDelta(double[][] expected, double[][] actual, double delta) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], delta);
        }
    }

    /**
     * random doubles
     * uses random array length
     */
    public static double[] randomDoubles() {
        return randomDoubles(ThreadLocalRandom.current().nextInt(2, 5));
    }

    /**
     * random doubles
     * uses provided array length
     */
    public static double[] randomDoubles(int len) {
        double[] result = new double[intPow(2, len)];
        for (int i = 0; i < result.length; i++) {
            result[i] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
        }
        return result;
    }

    /**
     * random doubles
     * uses random array length
     */
    public static double[][] randomDoubles2D() {
        return randomDoubles2D(ThreadLocalRandom.current().nextInt(2, 5));
    }

    /**
     * random doubles
     * uses provided array length
     */
    public static double[][] randomDoubles2D(int len) {
        double[][] result = new double[intPow(2, len)][intPow(2, len)];
        for (int i = 0; i < result.length; i++) {
            for(int j=0; j < result.length; j++){
                result[i][j] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
            }
        }
        return result;
    }
}
