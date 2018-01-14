package gebond.ip.math.func.compression;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Created by Gleb on 22.10.2017.
 */
@DisplayName("compression tests")
public class CompressionTest {

    private static Array1DSetting newSetting(double[] array, double rate) {
        return new Array1DSetting(array, rate);
    }

    private static Array2DSetting newSetting(double[][] array, double rate) {
        return new Array2DSetting(array, rate);
    }

    @Nested
    @DisplayName("huffman 1D")
    class Huffman1DTests {

        private Compression<Array1DSetting> compression = new HuffmanCompression1D();

        @Test
        @DisplayName("compress rate = 50")
        public void compression_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};

            Array1DSetting setting = newSetting(input, 50.0);
            double[] result = compression.compress(setting).getTarget();
            double[] answer = new double[]{
                    0.0, 0.0, 3.0,
                    0.0, 2.0, 3.0,
                    0.0, 2.0, 3.0};

            assertArrayEquals(answer, result);
        }

        @Test
        @DisplayName("compress rate = 12")
        public void compression_less_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};

            Array1DSetting setting = newSetting(input, 12.0);
            double[] result = compression.compress(setting).getTarget();
            double[] answer = new double[]{
                    0.0, 0.0, 0.0,
                    0.0, 0.0, 3.0,
                    0.0, 0.0, 3.0};

            assertArrayEquals(answer, result);
        }

        @Test
        @DisplayName("compress rate = 85")
        public void compression_more_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};

            Array1DSetting setting = newSetting(input, 85.0);
            double[] result = compression.compress(setting).getTarget();
            double[] answer = new double[]{
                    0.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};

            assertArrayEquals(answer, result);
        }
    }

    @Nested
    @DisplayName("huffman 2D")
    class Huffman2DTests {

        private Compression<Array2DSetting> compression = new HuffmanCompression2D();

        @Test
        @DisplayName("compress rate = 50")
        public void compression_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};

            Array2DSetting setting = newSetting(input, 50.0);
            double[][] result = compression.compress(setting).getTarget();
            double[][] answer = new double[][]{
                    {0.0, 0.0, 3.0},
                    {0.0, 2.0, 3.0},
                    {0.0, 2.0, 3.0}};

            assertArrayEquals(answer, result);
        }

        @Test
        @DisplayName("compress rate = 12")
        public void compression_less_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};

            Array2DSetting setting = newSetting(input, 12.0);
            double[][] result = compression.compress(setting).getTarget();
            double[][] answer = new double[][]{
                    {0.0, 0.0, 0.0},
                    {0.0, 0.0, 3.0},
                    {0.0, 0.0, 3.0}};

            assertArrayEquals(answer, result);
        }

        @Test
        @DisplayName("compress rate = 85")
        public void compression_more_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};

            Array2DSetting setting = newSetting(input, 85.0);
            double[][] result = compression.compress(setting).getTarget();
            double[][] answer = new double[][]{
                    {0.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};

            assertArrayEquals(answer, result);
        }
    }
}