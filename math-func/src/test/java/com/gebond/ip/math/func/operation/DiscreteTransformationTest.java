package com.gebond.ip.math.func.operation;

import static com.gebond.ip.math.commons.util.TestHelper.assertArrayEqualsWithDelta;
import static com.gebond.ip.math.commons.util.TestHelper.randomDoubles2D;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.transform.DiscreteTransformation2D;
import com.gebond.ip.math.func.transform.FourierTransform;
import com.gebond.ip.model.array.Vector3D;
import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.DiscreteSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Created by Gleb on 21.01.2018.
 */
@DisplayName("Discrete Transformations")
public class DiscreteTransformationTest {

  private DiscreteTransformation2D testee = new DiscreteTransformation2D();

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
        .withDiscrete(
            DiscreteSetting.newDiscreteBuilder().withP(2).withS(1).withN(1).withSize(2).build())
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
        .withDiscrete(
            DiscreteSetting.newDiscreteBuilder().withP(2).withS(2).withN(1).withSize(4).build())
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
        .withDiscrete(
            DiscreteSetting.newDiscreteBuilder().withP(2).withS(3).withN(1).withSize(8).build())
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
  @Disabled
  void testProcess_random() {
    double[][] array = randomDoubles2D();

    FourierContext.FourierContext2D context = testee.process(FourierContext
        .start2DBuilder(array)
        .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
        .withDiscrete(DiscreteSetting.newDiscreteBuilder().withP(2).build())
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
    FourierContext.FourierContext2D fourierContext = FourierContext
        .start2DBuilder(new double[][]{{1.0, 2.0}, {1.0, 2.0}})
        .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
        .withDiscrete(DiscreteSetting.newDiscreteBuilder().withP(2).withS(2).build())
        .build();
    fourierContext.getFourierData().setArray(new double[][]{{1.0}, {3.0}});

    assertThrows(IllegalArgumentException.class,
        () -> testee.process(fourierContext));
  }

  @Test
  @DisplayName("throws ex, len = {2, 3}")
  void testProcess_throwsException_23() {
    FourierContext.FourierContext2D fourierContext = FourierContext
        .start2DBuilder(new double[][]{{1.0, 2.0}, {1.0, 2.0}})
        .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
        .withDiscrete(DiscreteSetting.newDiscreteBuilder().withP(2).withS(2).build())
        .build();
    fourierContext.getFourierData().setArray(new double[][]{{1.0, 2.0, 3.0}, {1.0, 2.0, 3.0}});

    assertThrows(IllegalArgumentException.class,
        () -> testee.process(fourierContext));
  }

  @Test
  @DisplayName("throws ex, len = 0")
  void testProcess_throwsException_0() throws NoSuchFieldException, IllegalAccessException {
    FourierContext.FourierContext2D fourierContext = FourierContext
        .start2DBuilder(new double[][]{{1.0, 2.0}, {1.0, 2.0}})
        .withCompression(CompressionSetting.of(CompressionSetting.MIN_COMPRESSION_RATE))
        .withDiscrete(DiscreteSetting.newDiscreteBuilder().withP(2).withS(2).build())
        .build();
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
        .withDiscrete(
            DiscreteSetting.newDiscreteBuilder().withP(2).withS(2).withN(1).withSize(4).build())
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

  @Test
  @DisplayName("complete analysis only, p=2 s=2 n=1 len=4")
  void analysis_accept() {
    Vector3D<Integer> params = new Vector3D<>(2, 2, 1);
    double[] array = new double[]{10.0, -5.0, 1.0, 25.0};

    double[] result = FourierTransform.DiscreteFourierTransform.doAnalysis(params, array);

    assertEquals(31, result[0]);
  }
}