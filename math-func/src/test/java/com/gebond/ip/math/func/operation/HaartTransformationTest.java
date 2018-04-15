package com.gebond.ip.math.func.operation;

import static com.gebond.ip.math.commons.util.TestHelper.DELTA;
import static com.gebond.ip.math.commons.util.TestHelper.assertArrayEqualsWithDelta;
import static com.gebond.ip.math.commons.util.TestHelper.randomDoubles;
import static com.gebond.ip.math.commons.util.TestHelper.randomDoubles2D;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.transform.HaartTransformation1D;
import com.gebond.ip.math.func.transform.HaartTransformation2D;
import com.gebond.ip.model.setting.CompressionSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Created by Gleb on 21.01.2018.
 */
@DisplayName("Haart Operations")
public class HaartTransformationTest {

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
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[] result = context.getFourierData().getArray1DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(array, result);
    }

    @Test
    @DisplayName("complete, len = 8")
    void testProcess_doubledSize() {
      double[] array = new double[]{1.0, 2.0, -1.5, 4.5, -1.0, 22.0, -11.5, 40.5};

      FourierContext.FourierContext1D context = testee.process(FourierContext
          .start1DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[] result = context.getFourierData().getArray1DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(array, result);
    }

    @DisplayName("complete, random array1d")
    @RepeatedTest(10)
    void testProcess_random() {
      double[] array = randomDoubles();

      FourierContext.FourierContext1D context = testee.process(FourierContext
          .start1DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[] result = context.getFourierData().getArray1DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(array, result, DELTA);
    }

    @Test
    @DisplayName("throws ex, len = 3")
    void testProcess_throwsException_n3() {
      FourierContext.FourierContext1D fourierContext = FourierContext
          .fromArray(new double[]{1.0, 2.0});
      fourierContext.getFourierData().setArray(new double[]{1.0, 2.0, 3.0});

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(fourierContext));
    }

    @Test
    @DisplayName("throws ex, len = 0")
    void testProcess_throwsException_n0() {
      FourierContext.FourierContext1D fourierContext = FourierContext
          .fromArray(new double[]{1.0, 2.0});
      fourierContext.getFourierData().setArray(new double[]{});

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(fourierContext));
    }

    @Test
    @DisplayName("complete with max compression, len = 8")
    void testProcess_compression() {
      double[] array = new double[]{1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0};

      FourierContext.FourierContext1D context = testee.process(FourierContext
          .start1DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MAX_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());

      double[] result = context.getFourierData().getArray1DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(new double[]{0, 0, 0, 0, 0, 0, 0, 0}, result);
    }
  }

  @Nested
  @DisplayName("2D")
  class Transformation2D {

    private HaartTransformation2D testee = new HaartTransformation2D();

    @BeforeEach
    private void init() {
    }

    @Test
    @DisplayName("complete, len = {2, 2}")
    void testProcess() {
      double[][] array = new double[][]{{1.0, 2.0}, {3.0, 4.0}};

      FourierContext.FourierContext2D context = testee.process(FourierContext
          .start2DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[][] result = context.getFourierData().getArray2DCopy();
      assertNotNull(result, "array 2d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(array, result);
    }

    @Test
    @DisplayName("complete, len = {4, 4}")
    void testProcess_doubledSize() {
      double[][] array = new double[][]{
          {1.0, 2.0, -1.5, 4.5},
          {10.5, 2.0, -1.9, 4.5},
          {1.0, 20.0, -1.5, -4.5},
          {1.0, 2.5, -10.5, 4.5}};

      FourierContext.FourierContext2D context = testee.process(FourierContext
          .start2DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[][] result = context.getFourierData().getArray2DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEqualsWithDelta(array, result);
    }

    @Test
    @DisplayName("complete, len = {8, 8}")
    void testProcess_doubledSize_2() {
      double[][] array = new double[][]{
          {1.0, 2.0, -1.5, 4.5, 10.5, 2.0, -1.9, 4.5},
          {10.5, 2.0, -1.9, 4.5, 10.5, 2.0, -1.9, 4.5},
          {1.0, 20.0, -1.5, -4.5, 1.0, 2.0, -1.5, 4.5},
          {1.0, 2.5, -10.5, 4.5, -1.5, -4.5, 10.5, 2.0},
          {1.0, 2.0, -1.5, 4.5, 1.0, 2.0, -1.5, 4.5},
          {10.5, 2.0, -1.9, 4.5, -1.5, -4.5, 1.0, 2.0},
          {1.0, 20.0, -1.5, -4.5, 10.5, 2.0, -1.9, 4.5},
          {1.0, 2.5, -10.5, 4.5, 10.5, 2.0, -1.9, 4.5}};

      FourierContext.FourierContext2D context = testee.process(FourierContext
          .start2DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[][] result = context.getFourierData().getArray2DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEqualsWithDelta(array, result);
    }

    @RepeatedTest(10)
    @DisplayName("complete, random array2d")
    void testProcess_random() {
      double[][] array = randomDoubles2D();

      FourierContext.FourierContext2D context = testee.process(FourierContext
          .start2DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());
      double[][] result = context.getFourierData().getArray2DCopy();
      assertNotNull(result, "array 1d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEqualsWithDelta(array, result);
    }

    @Test
    @DisplayName("throws ex, len = {2, 1}")
    void testProcess_throwsException_21() {
      FourierContext.FourierContext2D fourierContext = FourierContext.
          fromArray(new double[][]{{1.0, 2.0}, {1.0, 2.0}});
      fourierContext.getFourierData().setArray(new double[][]{{1.0}, {3.0}});

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(fourierContext));
    }

    @Test
    @DisplayName("throws ex, len = {2, 3}")
    void testProcess_throwsException_23() {
      FourierContext.FourierContext2D fourierContext = FourierContext.
          fromArray(new double[][]{{1.0, 2.0}, {1.0, 2.0}});
      fourierContext.getFourierData().setArray(new double[][]{{1.0, 2.0, 3.0}, {1.0, 2.0, 3.0}});

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(fourierContext));
    }

    @Test
    @DisplayName("throws ex, len = 0")
    void testProcess_throwsException_0() throws NoSuchFieldException, IllegalAccessException {
      FourierContext.FourierContext2D fourierContext = FourierContext
          .fromArray(new double[][]{{1.0, 2.0}, {1.0, 2.0}});
      fourierContext.getFourierData().setArray(new double[][]{{}});

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(fourierContext));
    }

    @Test
    @DisplayName("complete with max compression, len = {4, 4}")
    void testProcess_compression() {
      double[][] array = new double[][]{
          {1.0, 2.0, 3.0, 4.0},
          {5.0, 6.0, 7.0, 8.0},
          {1.0, 2.0, 3.0, 4.0},
          {5.0, 6.0, 7.0, 8.0}};

      FourierContext.FourierContext2D context = testee.process(FourierContext
          .start2DBuilder(array)
          .withCompression(CompressionSetting.of(CompressionSetting.MAX_COMPRESSION_RATE))
          .build());

      assertNotNull(context);
      assertFalse(context.isClosed());

      double[][] result = context.getFourierData().getArray2DCopy();
      assertNotNull(result, "array 2d mut be not null");
      assertEquals(array.length, result.length, "result array must have the same size");
      assertArrayEquals(new double[][]{
          {0, 0, 0, 0},
          {0, 0, 0, 0},
          {0, 0, 0, 0},
          {0, 0, 0, 0}}, result);
    }
  }
}