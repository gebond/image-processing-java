package com.gebond.ip.math.func.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by Gleb on 21.01.2018.
 */
@DisplayName("Haart Operations tests")
class HaartTransformation1DTest {
    @Nested
    @DisplayName("1D")
    class Transformation1D {
        private HaartTransformation1D testee = new HaartTransformation1D();
        private FourierContext.FourierContext1D context;

        @BeforeEach
        private void init() {
            context = null;
        }

        @Test
        @DisplayName("valid array is processed")
        void testProcess() {
            double[] array = new double[]{1.0, 2.0, -1.5, 4.5};
            FourierContext.FourierContext1D context = testee.process(FourierContext.fromArray(array));

            assertNotNull(context);
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertEquals(array.length, context.getFourierData().getSize(), "result array must have the same size");
            assertArrayEquals(array, context.getFourierData().getArray1D());
        }

        @Test
        @DisplayName("valid arrayX2 is processed")
        void testProcessDoubleSize() {
            double[] array = new double[]{1.0, 2.0, -1.5, 4.5, -1.0, 22.0, -11.5, 40.5};
            FourierContext.FourierContext1D context = testee.process(FourierContext.fromArray(array));

            assertNotNull(context);
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertEquals(array.length, context.getFourierData().getSize(), "result array must have the same size");
            assertArrayEquals(array, context.getFourierData().getArray1D());
        }
    }
}