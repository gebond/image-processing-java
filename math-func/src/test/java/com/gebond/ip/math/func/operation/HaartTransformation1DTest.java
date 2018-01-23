package com.gebond.ip.math.func.operation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Gleb on 21.01.2018.
 */
@DisplayName("Haart Operations tests")
class HaartTransformation1DTest {
    @Nested
    @DisplayName("1D")
    class Transformation1D {
        private HaartTransformation1D testee;
        private FourierContext.FourierContextBuilder contextBuilder;

        @BeforeEach
        private void init() {
            testee = new HaartTransformation1D();
            contextBuilder = FourierContext.startBuild();
        }

        @Test
        @DisplayName("valid array is processed")
        void testProcess() {
            double[] array = new double[]{1.0, 2.0, -1.5, 4.5};
            FourierContext context = testee.process(contextBuilder.withArray(array).build());

            assertNotNull(context);
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertNull(context.getFourierData().getArray2D(), "array 2d must be null");
            assertEquals(array.length, context.getFourierData().getArray1D().length, "result array must have the same size");
        }
    }
}