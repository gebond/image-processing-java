package com.gebond.ip.math.commons.util;

import org.junit.jupiter.api.Test;

import static com.gebond.ip.math.commons.util.TestHelper.assertArrayEqualsWithDelta;

/**
 * Created on 04/03/18.
 */
class ImageUtilTest {

    @Test
    void normalizePixelArrayTest() {
        double[][] input = new double[][]{
                {100, 256, 255, -2},
                {-100, 200, 35, 41},
                {1, 25, 300, 45},
                {0, -20, 39, 400}
        };
        double[][] normalized = new double[][]{
                {100, 255, 255, 0},
                {0, 200, 35, 41},
                {1, 25, 255, 45},
                {0, 0, 39, 255}
        };

        assertArrayEqualsWithDelta(normalized, ImageUtil.normalizePixelArray(input));
    }
}