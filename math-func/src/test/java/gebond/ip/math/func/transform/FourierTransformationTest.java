package gebond.ip.math.func.transform;

import mockit.Deencapsulation;
import mockit.integration.junit4.JMockit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

import static gebond.ip.math.common.util.ArrayUtil.arrayCopy;
import static gebond.ip.math.common.util.MathUtil.intPow;
import static org.apache.commons.math3.util.FastMath.pow;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Gleb on 21.10.2017.
 */
@DisplayName("fourier transformation tests")
@RunWith(JMockit.class)
class FourierTransformationTest {

    @Nested
    @DisplayName("haart transformation 1D")
    class HaartTransformation1D {

        private FourierTransformation transformation = new HaartFourierTransformation1D();

        @Test
        @DisplayName("when odd throws exception")
        public void haart_transf_init_fail_args() {
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            getNewFourierData(new double[]{1.0})
                    ));
        }

        @Test
        @DisplayName("when input is null throws exception")
        public void haart_transf_init_fail_args_2() {
            FourierData fourierData = getNewFourierData(new double[]{});
            Deencapsulation.setField(fourierData, "array1D", null);
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            fourierData
                    ));
        }

        @Test
        @DisplayName("when input size = 0 throws exception")
        public void haart_transf_init_fail_args_3() {
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            getNewFourierData(new double[]{})
                    ));
        }

        @Test
        @DisplayName("input array analysed")
        public void haart_transf_init_analysis() {
            double[] func_values = new double[]{1.0, 2.0, 3.0, 4.0};
            FourierData fourierData = getNewFourierData(func_values);
            transformation.analysis(fourierData);

            double[] processed = fourierData.getArray1D();

            assertNotNull(processed);
            assertEquals(4, processed.length);
            assertEquals(-0.5, processed[3]);
            assertEquals(-1.0, processed[1]);
        }

        @Test
        @DisplayName("input array synthesised")
        public void haart_transf_init_synthesis() {
            double[] func_values = new double[]{1.0, 2.0, 3.0, 4.0};
            FourierData fourierData = getNewFourierData(func_values);
            transformation.synthesis(fourierData);

            double[] processed = fourierData.getArray1D();

            assertNotNull(processed);
            assertEquals(4, processed.length);
            assertEquals(-5.0, processed[3]);
            assertEquals(0.0, processed[1]);
        }

        @DisplayName("random array input analysed and synthesised fully")
        @RepeatedTest(4)
        public void haart_transf_full() {
            int random = ThreadLocalRandom.current().nextInt(2, 5);
            double[] func_values = getRandomDoubles(random);
            double[] func_values_copy = new double[func_values.length];
            System.arraycopy(func_values, 0, func_values_copy, 0, func_values.length);
            FourierData fourierData = getNewFourierData(func_values);
            transformation.analysis(fourierData);
            transformation.synthesis(fourierData);
            assertArrayEquals(func_values_copy, fourierData.getArray1D(), pow(10, -10));
        }
    }

    @Nested
    @DisplayName("haart transformation 2D")
    class HaartTransformation2D {

        private FourierTransformation transformation = new HaartFourierTransformation1D();

        @Test
        @DisplayName("when odd throws exception")
        public void haart_transf_init_fail_args() {
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            getNewFourierData(new double[][]{{1.0}})
                    ));
        }

        @Test
        @DisplayName("when input is null throws exception")
        public void haart_transf_init_fail_args_2() {
            FourierData fourierData = getNewFourierData(new double[][]{});
            Deencapsulation.setField(fourierData, "array2D", null);
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            fourierData
                    ));
        }

        @Test
        @DisplayName("when input size = 0 throws exception")
        public void haart_transf_init_fail_args_3() {
            assertThrows(IllegalArgumentException.class,
                    () -> transformation.analysis(
                            getNewFourierData(new double[][]{})
                    ));
        }

        @Test
        @DisplayName("input 2d array analysed")
        public void haart_transf_init_analysis() {
            double[][] func_values = new double[][]{
                    {1.0, 2.0, 3.0, 4.0},
                    {-1.0, -2.0, -3.0, -4.0},
                    {10.0, 20.0, 30.0, 40.0},
                    {0.0, -2.0, 30.0, -4.0}};
            FourierData fourierData = getNewFourierData(func_values);
            transformation.analysis(fourierData);

            double[][] processed = fourierData.getArray2D();

            assertNotNull(processed);
            assertEquals(func_values.length, processed.length);
            assertEquals(func_values[0].length, processed[0].length);
            assertEquals(-1.0, processed[0][2]);
            assertEquals(4.25, processed[1][1]);
            assertEquals(-0.5, processed[2][3]);
            assertEquals(9.5, processed[3][0]);
        }

        @Test
        @DisplayName("input 2d array synthesised")
        public void haart_transf_init_synthesis() {
            double[][] func_values = new double[][]{
                    {1.0, 2.0, 3.0, 4.0},
                    {-1.0, -2.0, -3.0, -4.0},
                    {10.0, 20.0, 30.0, 40.0},
                    {0.0, -2.0, 30.0, -4.0}};
            FourierData fourierData = getNewFourierData(func_values);
            transformation.synthesis(fourierData);

            double[][] processed = fourierData.getArray2D();

            assertNotNull(processed);
            assertEquals(func_values.length, processed.length);
            assertEquals(func_values[0].length, processed[0].length);
            assertEquals(30.0, processed[0][2]);
            assertEquals(0.0, processed[1][1]);
            assertEquals(-4.0, processed[2][3]);
            assertEquals(-16.0, processed[3][0]);
        }

        @DisplayName("random 2d array input analysed and synthesised fully")
        @RepeatedTest(4)
        public void haart_transf_full() {
            int random = ThreadLocalRandom.current().nextInt(2, 5);
            double[][] func_values = getRandomDoubles2D(random);
            double[][] func_values_copy = new double[func_values.length][func_values.length];
            arrayCopy(func_values, func_values_copy);
            FourierData fourierData = getNewFourierData(func_values);
            transformation.analysis(fourierData);
            transformation.synthesis(fourierData);
            for (int i = 0; i < func_values.length; i++) {
                assertArrayEquals(func_values_copy[i], fourierData.getArray2D()[i], pow(10, -10));
            }
        }
    }

    private static FourierData getNewFourierData(double[] array) {
        return new FourierData(array);
    }

    private static FourierData getNewFourierData(double[][] array) {
        return new FourierData(array);
    }

    private static double[] getRandomDoubles(int k) {
        double[] result = new double[intPow(2, k)];
        for (int i = 0; i < result.length; i++) {
            result[i] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
        }
        return result;
    }

    private static double[][] getRandomDoubles2D(int k) {
        int size = intPow(2, k);
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = ThreadLocalRandom.current().nextDouble(-100.0, 100.0);
            }
        }
        return result;
    }
}