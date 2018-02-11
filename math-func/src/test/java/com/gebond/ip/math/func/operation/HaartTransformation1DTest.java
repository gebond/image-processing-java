package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.compression.CompressionSetting;
import com.gebond.ip.math.func.transform.HaartTransformation1D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Gleb on 21.01.2018.
 */
@DisplayName("Haart Operations tests")
class HaartTransformation1DTest {
    @Nested
    @DisplayName("1D")
    class Transformation1D {
        private HaartTransformation1D testee = new HaartTransformation1D();

        @BeforeEach
        private void init() {
        }

        @Test
        @DisplayName("complete, len = 4")
        void testProcess() {
            double[] array = new double[]{1.0, 2.0, -1.5, 4.5};

            FourierContext.FourierContext1D context = testee.process(FourierContext
                    .start1DBuilder(array)
                    .withCompression(CompressionSetting.of(10.0))
                    .build());

            assertNotNull(context);
            assertFalse(context.isClosed());
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertEquals(array.length, context.getFourierData().getSize(), "result array must have the same size");
            assertArrayEquals(array, context.getFourierData().getArray1D());
        }

        @Test
        @DisplayName("complete, len = 8")
        void testProcess_doubledSize() {
            double[] array = new double[]{1.0, 2.0, -1.5, 4.5, -1.0, 22.0, -11.5, 40.5};

            FourierContext.FourierContext1D context = testee.process(FourierContext
                    .start1DBuilder(array)
                    .withCompression(CompressionSetting.of(10.0))
                    .build());

            assertNotNull(context);
            assertFalse(context.isClosed());
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertEquals(array.length, context.getFourierData().getSize(), "result array must have the same size");
            assertArrayEquals(array, context.getFourierData().getArray1D());
        }

        @Test
        @DisplayName("throws ex, len = 3")
        void testProcess_throwsException_n3() {
            FourierContext.FourierContext1D fourierContext = FourierContext.fromArray(new double[]{1.0, 2.0});
            fourierContext.getFourierData().setArray(new double[]{1.0, 2.0, 3.0});

            testee.process(fourierContext);

            assertTrue(fourierContext.isClosed());
        }

        @Test
        @DisplayName("throws ex, len = 0")
        void testProcess_throwsException_n0() {
            FourierContext.FourierContext1D fourierContext = FourierContext.fromArray(new double[]{1.0, 2.0});
            fourierContext.getFourierData().setArray(new double[]{});

            testee.process(fourierContext);

            assertTrue(fourierContext.isClosed());
        }

        @Test
        @DisplayName("complete with compression, len = 8")
        void testProcess_compression() {
            double[] array = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};

            FourierContext.FourierContext1D context = testee.process(FourierContext
                    .start1DBuilder(array)
                    .withCompression(CompressionSetting.of(50.0))
                    .build());

//            double[] array = new double[]{0};

            assertNotNull(context);
            assertFalse(context.isClosed());
            assertNotNull(context.getFourierData().getArray1D(), "array 1d mut be not null");
            assertEquals(array.length, context.getFourierData().getSize(), "result array must have the same size");
            assertArrayEquals(array, context.getFourierData().getArray1D());
        }
    }
}