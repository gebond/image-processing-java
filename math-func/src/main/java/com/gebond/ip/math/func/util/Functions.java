package com.gebond.ip.math.func.util;

import static com.gebond.ip.math.func.util.Numerations.paley;
import static org.apache.commons.math3.util.FastMath.*;

/**
 * Created by Gleb on 18.10.2017.
 */
public class Functions {

    private Functions() {
    }

    /**
     * Rademacher function implementation with strict x
     * r_0(x) for x: from 0 to 1
     *
     * @param x
     * @return
     */
    public static double rademacher(double x) {
        x = normalize(x);
        if ((x >= 0 && x < 0.5) || x == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Rademacher function implementation for any x
     * r_k(x) for real x
     *
     * @param k
     * @param x
     * @return
     */
    public static double rademacher(int k, double x) {
        if (k < 0) {
            throw new IllegalArgumentException("For Rademacher Function: k > 0. Actual k = " + k);
        }
        return rademacher(pow(2, k) * x);
    }

    /**
     * @param m
     * @param k
     * @param x
     * @return
     */
    public static double haart(int m, int k, double x) {
        if (k < 0 || m < 0 || k > pow(2, m)) {
            throw new IllegalArgumentException("Wrong args Haart function: k = 0, 1, ..., 2^m - 1");
        }
        if (k == 0 && m == 0) {
            return 1.0;
        }
        x = normalize(x);
        double powM = pow(2, m);
        if (x >= ((double) k - 1) / powM && x < ((double) k - 0.5) / powM) {
            //return Math.Sqrt(powM);
            return 1.0;
        }
        if (x >= ((double) k - 0.5) / powM && x < ((double) k) / powM) {
            //return -Math.Sqrt(powM);
            return -1.0;
        }
        return 0.0;
    }

    /**
     * @param n
     * @param x
     * @return
     */
    public static double haart(int n, double x) {
        if (n < 0) {
            throw new IllegalArgumentException("Wrong args Haart function: n >= 0");
        }
        if (n == 0) {
            return haart(0, 0, x);
        }
        if (n == 1) {
            return haart(0, 1, x);
        }
        int m = (int) (log(2, n));
        int k = 1;
        int targetK = (int) pow(2, m);
        while (targetK != n) {
            targetK++;
            k++;
        }
        return haart(m, k, x);
    }

    /**
     * @param n
     * @param x
     * @return
     */
    public static double walsh(int n, double x) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 must be for Walsh Function");
        }
        double result = 1.0;
        if (n == 0) {
            return result;
        }
        int[] eps = paley(n); // get array of {1, 0} according to Paley Numbers
        for (int i = 0; i < eps.length; i++) {
            result = result * pow(rademacher(i, x), eps[i]);
        }
        return result;
    }

    /**
     * converts input x into value from [0, 1]
     */
    private static double normalize(double x) {
        if (x >= 1) {
            x = x - (int) x;
        } else if (x < 0) {
            x = 1 - (abs(x) - (int) abs(x));
        }
        return x;
    }
}
