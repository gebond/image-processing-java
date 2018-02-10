package com.gebond.ip.math.commons.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gebond.ip.math.commons.util.ArrayUtil.arrayCopy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Gleb on 27.01.2018.
 */
@DisplayName("Array util tests")
class ArrayUtilTest {

    @Nested
    @DisplayName("1D arrays")
    class Array1D {

        @Test
        @DisplayName("1D array copied")
        void arrayCopyTest() {
            double[] source = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
            double[] target = new double[source.length];

            arrayCopy(source, target);

            assertEquals(source.length, target.length);
            assertArrayEquals(source, target);
        }

        @Test
        @DisplayName("1D array copied and not changed after target's changes")
        void arrayCopyChangedSourceTest() {
            double[] source = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
            double[] source_copy = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
            double[] target = new double[source.length];

            arrayCopy(source, target);

            source[0] = 10;
            source[1] = -100;
            assertEquals(source.length, target.length);
            assertArrayEquals(source_copy, target);
        }
    }

    @Nested
    @DisplayName("2D arrays")
    class Array2D {
        @Test
        @DisplayName("2D array copied")
        void arrayCopyTest() {
            double[][] source = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};
            double[][] target = new double[source.length][source[0].length];

            arrayCopy(source, target);

            assertEquals(source.length, target.length);
            assertEquals(source[0].length, target[0].length);
            assertArrayEquals(source, target);
        }

        @Test
        @DisplayName("2D array copied and not changed after target's changes")
        void arrayCopyChangedSourceTest() {
            double[][] source = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};
            double[][] source_copy = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};
            double[][] target = new double[source.length][source[0].length];

            arrayCopy(source, target);
            source[1][0] = -10.0;
            source[0][1] = 100.0;

            assertEquals(source.length, target.length);
            assertEquals(source[0].length, target[0].length);
            assertArrayEquals(source_copy, target);
        }
    }
}