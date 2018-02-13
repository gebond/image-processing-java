package com.gebond.ip.math.func.compression;

import com.gebond.ip.math.func.context.FourierContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Created by Gleb on 22.10.2017.
 */
@DisplayName("Compression Operations")
public class CompressionTest {

    @Nested
    @DisplayName("huffman 1D")
    class Huffman1DTests {

        private CompressionOperation1D compression = new CompressionOperation1D();

        @Test
        @DisplayName("compress rate = 50")
        public void compression_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};
            FourierContext.FourierContext1D context1D = FourierContext.start1DBuilder(input)
                    .withCompression(CompressionSetting.of(50.0))
                    .build();

            compression.apply(context1D);

            double[] answer = new double[]{
                    0.0, 0.0, 3.0,
                    0.0, 2.0, 3.0,
                    0.0, 2.0, 3.0};
            assertArrayEquals(answer, context1D.getFourierData().getArray1DCopy());
        }

        @Test
        @DisplayName("compress rate = 88")
        public void compression_more_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};
            FourierContext.FourierContext1D context1D = FourierContext.start1DBuilder(input)
                    .withCompression(CompressionSetting.of(88.0))
                    .build();

            compression.apply(context1D);

            double[] answer = new double[]{
                    0.0, 0.0, 0.0,
                    0.0, 0.0, 3.0,
                    0.0, 0.0, 3.0};
            assertArrayEquals(answer, context1D.getFourierData().getArray1DCopy());
        }

        @Test
        @DisplayName("compress rate = 15")
        public void compression_less_half() {
            double[] input = new double[]{
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};
            FourierContext.FourierContext1D context1D = FourierContext.start1DBuilder(input)
                    .withCompression(CompressionSetting.of(15.0))
                    .build();

            compression.apply(context1D);

            double[] answer = new double[]{
                    0.0, 2.0, 3.0,
                    1.0, 2.0, 3.0,
                    1.0, 2.0, 3.0};
            assertArrayEquals(answer, context1D.getFourierData().getArray1DCopy());
        }
    }

    @Nested
    @DisplayName("huffman 2D")
    class Huffman2DTests {

        private CompressionOperation2D compression = new CompressionOperation2D();

        @Test
        @DisplayName("compress rate = 50")
        public void compression_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};
            FourierContext.FourierContext2D context2D = FourierContext.start2DBuilder(input)
                    .withCompression(CompressionSetting.of(50.0))
                    .build();

            compression.apply(context2D);

            double[][] answer = new double[][]{
                    {0.0, 0.0, 3.0},
                    {0.0, 2.0, 3.0},
                    {0.0, 2.0, 3.0}};
            assertArrayEquals(answer, context2D.getFourierData().getArray2DCopy());
        }

        @Test
        @DisplayName("compress rate = 12")
        public void compression_less_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};
            FourierContext.FourierContext2D context2D = FourierContext.start2DBuilder(input)
                    .withCompression(CompressionSetting.of(12.0))
                    .build();

            compression.apply(context2D);

            double[][] answer = new double[][]{
                    {0.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};
            assertArrayEquals(answer, context2D.getFourierData().getArray2DCopy());
        }

        @Test
        @DisplayName("compress rate = 85")
        public void compression_more_half() {
            double[][] input = new double[][]{
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0},
                    {1.0, 2.0, 3.0}};
            FourierContext.FourierContext2D context2D = FourierContext.start2DBuilder(input)
                    .withCompression(CompressionSetting.of(85.0))
                    .build();

            compression.apply(context2D);

            double[][] answer = new double[][]{
                    {0.0, 0.0, 0.0},
                    {0.0, 0.0, 3.0},
                    {0.0, 0.0, 3.0}};
            assertArrayEquals(answer, context2D.getFourierData().getArray2DCopy());
        }
    }
}