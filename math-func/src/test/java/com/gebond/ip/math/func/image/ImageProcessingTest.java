package com.gebond.ip.math.func.image;

import static com.gebond.ip.math.commons.util.TestHelper.assertArrayEqualsWithDelta;
import static com.gebond.ip.math.func.image.ImageTestHelper.assertImageEqualsWithSizes;
import static com.gebond.ip.math.func.image.ImageTestHelper.getImageUsingFileName;
import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;
import static com.gebond.ip.model.setting.ImageSetting.ImageSchema.RGB;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;
import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.Vector;
import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

/**
 * Created on 17/02/18.
 */
@DisplayName("Image Operations")
@Disabled
public class ImageProcessingTest {

  private ImageContext buildImageContext(TransformSetting.TransformationType transformationType,
      Map<Integer, CompressionSetting> compressions) {
    return ImageContext.startBuilder()
        .withSetting(ImageSetting.startBuilder()
            .withSchema(RGB)
            .withCompressions(compressions)
            .build())
        .withSetting(TransformSetting.startBuilder()
            .withType(transformationType)
            .build())
        .build();
  }

  private ImageContext buildImageContext(BufferedImage bufferedImage,
      ImageSetting.SegmentSize segmentSize) {
    return ImageContext.startBuilder()
        .withSetting(ImageSetting.startBuilder()
            .withImage(bufferedImage)
            .withSegmentSize(segmentSize)
            .build())
        .build();
  }

  private void saveImage(BufferedImage image, String imageName) throws IOException {
    File outputfile = new File("target/OUT-" + imageName + ".png");
    ImageIO.write(image, "png", outputfile);
  }

  @Nested
  @DisplayName("Split and Build image")
  public class SplitAndBuildOperations {

    OperationManager<ImageContext> testee;

    @BeforeEach
    void init() {
      testee = new OperationManager<ImageContext>() {
        @Override
        public List<Operation<ImageContext>> getOperations() {
          return Arrays.asList(
              new ImageProcessing.SplittingOperation(),
              new ImageProcessing.BuildingOperation());
        }
      };
    }

    @Test
    @DisplayName("complete, 8x8.png, x8")
    void testProcess_8x8() throws IOException {
      BufferedImage input = getImageUsingFileName("8x8.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      testee.process(imageContext);

      assertEquals(
          imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(
          1,
          imageContext.getPixelList().size());
      assertEquals(
          imageContext.getColumnCount() * imageContext.getResultSetting().getImageSetting()
              .getSegmentSize().getValue(),
          imageContext.getImage().getWidth());
      assertEquals(
          imageContext.getRowCount() * imageContext.getResultSetting().getImageSetting()
              .getSegmentSize().getValue(),
          imageContext.getImage().getHeight());
      saveImage(imageContext.getImage(), "8x8");

      assertImageEqualsWithSizes(input, imageContext.getImage());
    }

    @Test
    @DisplayName("complete, 9x12.png, x8")
    void testProcess_9x12() throws IOException {
      BufferedImage input = getImageUsingFileName("9x12.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(1, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "9x12");
    }

    @Test
    @DisplayName("throws ex, 6x10.png, x8")
    void testProcess_6x10() throws IOException {
      BufferedImage input = getImageUsingFileName("6x10.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(imageContext));
    }

    @Test
    @DisplayName("throws ex, 13x6.png, x8")
    void testProcess_13x6() throws IOException {
      BufferedImage input = getImageUsingFileName("13x6.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      assertThrows(IllegalArgumentException.class,
          () -> testee.process(imageContext));
    }

    @Test
    @DisplayName("complete, 8x16.png, x8")
    void testProcess_8x16() throws IOException {
      BufferedImage input = getImageUsingFileName("8x16.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(2, imageContext.getPixelList().size());
      assertImageEqualsWithSizes(input, imageContext.getImage());

      saveImage(imageContext.getImage(), "8x16");
    }

    @Test
    @DisplayName("complete, 16x8.png, x8")
    void testProcess_16x8() throws IOException {
      BufferedImage input = getImageUsingFileName("16x8.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(2, imageContext.getPixelList().size());
      assertImageEqualsWithSizes(input, imageContext.getImage());

      saveImage(imageContext.getImage(), "16x8");
    }

    @Test
    @DisplayName("complete, 64x64.png, x8")
    void testProcess_64x64() throws IOException {
      BufferedImage input = getImageUsingFileName("64x64.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(64, imageContext.getPixelList().size());
      assertImageEqualsWithSizes(input, imageContext.getImage());

      saveImage(imageContext.getImage(), "64x64");
    }

    @Test
    @DisplayName("complete, 6x5.png, x4")
    void testProcess_6x5_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("6x5.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(1, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "6x5_x4");

      assertEquals(input.getRGB(0, 0), imageContext.getImage().getRGB(0, 0));
      assertEquals(input.getRGB(3, 3), imageContext.getImage().getRGB(3, 3));
      assertEquals(input.getRGB(0, 3), imageContext.getImage().getRGB(0, 3));
      assertEquals(input.getRGB(3, 3), imageContext.getImage().getRGB(3, 3));
      assertEquals(input.getRGB(2, 2), imageContext.getImage().getRGB(2, 2));
    }

    @Test
    @DisplayName("complete, 8x8.png, x4")
    void testProcess_8x8_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("8x8.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(4, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "8x8_x4");

      assertImageEqualsWithSizes(input, imageContext.getImage());
    }

    @Test
    @DisplayName("complete, 6x10.png, x4")
    void testProcess_6x10_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("6x10.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(2, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "6x10_x4");
    }

    @Test
    @DisplayName("complete, 9x12.png, x4")
    void testProcess_9x12_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("9x12.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(6, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "9x12_x4");
    }

    @Test
    @DisplayName("complete, 13x6.png, x4")
    void testProcess_13x6_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("13x6.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(3, imageContext.getPixelList().size());

      saveImage(imageContext.getImage(), "13x6_x4");
    }

    @Test
    @DisplayName("complete, 8x16.png, x4")
    void testProcess_8x16_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("8x16.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(8, imageContext.getPixelList().size());
      assertImageEqualsWithSizes(input, imageContext.getImage());

      saveImage(imageContext.getImage(), "8x16_x4");
    }

    @Test
    @DisplayName("complete, 16x8.png, x4")
    void testProcess_16x8_x4() throws IOException {
      BufferedImage input = getImageUsingFileName("16x8.png");
      ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

      testee.process(imageContext);

      assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(),
          imageContext.getPixelList().size());
      assertEquals(8, imageContext.getPixelList().size());
      assertImageEqualsWithSizes(input, imageContext.getImage());

      saveImage(imageContext.getImage(), "16x8_x4");
    }
  }

  @Nested
  @DisplayName("Consistent operations")
  public class ConsistentProcessingOperations {

    OperationManager<ImageContext> testee;
    ImageContext imageContext;

    @BeforeEach
    void init() {
      testee = new OperationManager<ImageContext>() {
        @Override
        public List<Operation<ImageContext>> getOperations() {
          return Arrays.asList(
              new ImageProcessing.ConsistentProcessingOperation());
        }
      };
    }

    @Nested
    @DisplayName("Walsh")
    public class WalshTransform {

      @BeforeEach
      void init() {
        imageContext = buildImageContext(
            TransformSetting.TransformationType.WALSH_TRANSFORM,
            new HashMap<Integer, CompressionSetting>() {
              {
                put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
                put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
                put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
              }
            });
      }

      @Test
      @DisplayName("complete, 2x2x3, walsh, min")
      void testProcess() {
        double[][] array1 = new double[][]{{3.0, 2.0}, {3.0, 2.0}};
        double[][] array2 = new double[][]{{1.0, 3.0}, {2.0, 1.0}};
        double[][] array3 = new double[][]{{2.0, 3.0}, {1.0, 3.0}};

        imageContext.setPixelList(Arrays.asList(
            new Vector<>(
                new Array2D(array1),
                new Array2D(array2),
                new Array2D(array3))
        ));

        testee.process(imageContext);

        assertArrayEquals(array1, imageContext.getPixelList().get(0).getX().getArray2DCopy());
        assertArrayEquals(array2, imageContext.getPixelList().get(0).getY().getArray2DCopy());
        assertArrayEquals(array3, imageContext.getPixelList().get(0).getZ().getArray2DCopy());
      }
    }

    @Nested
    @DisplayName("Haart")
    public class HaartTransform {

      @BeforeEach
      void init() {
        imageContext = buildImageContext(
            TransformSetting.TransformationType.HAART_TRANSFORM,
            new HashMap<Integer, CompressionSetting>() {
              {
                put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
                put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
                put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
              }
            });
      }

      @Test
      @DisplayName("complete, 2x2x3, haart, min")
      void testProcess() {
        double[][] array1 = new double[][]{{3.0, 2.0}, {3.0, 2.0}};
        double[][] array2 = new double[][]{{1.0, 3.0}, {2.0, 1.0}};
        double[][] array3 = new double[][]{{2.0, 3.0}, {1.0, 3.0}};

        imageContext.setPixelList(Arrays.asList(
            new Vector<>(
                new Array2D(array1),
                new Array2D(array2),
                new Array2D(array3))
        ));

        testee.process(imageContext);

        assertArrayEquals(array1, imageContext.getPixelList().get(0).getX().getArray2DCopy());
        assertArrayEquals(array2, imageContext.getPixelList().get(0).getY().getArray2DCopy());
        assertArrayEquals(array3, imageContext.getPixelList().get(0).getZ().getArray2DCopy());
      }
    }
  }

  @Nested
  @DisplayName("Concurrent operations")
  public class ConcurrentOperations {

    OperationManager<ImageContext> testee;

    @BeforeEach
    void init() {
      testee = new OperationManager<ImageContext>() {
        @Override
        public List<Operation<ImageContext>> getOperations() {
          return Arrays.asList(
              new ImageProcessing.ConcurrentOperation());
        }
      };
    }

    @Test
    @DisplayName("complete, 2x2, haart, min")
    void testProcess() {
      ImageContext imageContext = buildImageContext(
          TransformSetting.TransformationType.HAART_TRANSFORM,
          new HashMap<Integer, CompressionSetting>() {
            {
              put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
            }
          });

      double[][] array1 = new double[][]{{3.0, 2.0}, {3.0, 2.0}};
      double[][] array2 = new double[][]{{2.0, 3.0}, {2.0, 1.0}};
      double[][] array3 = new double[][]{{1.0, 2.0}, {1.0, 3.0}};

      imageContext.setPixelList(Arrays.asList(
          new Vector<>(
              new Array2D(array1),
              new Array2D(array2),
              new Array2D(array3))
      ));

      testee.process(imageContext);

      assertArrayEquals(array1, imageContext.getPixelList().get(0).getX().getArray2DCopy());
      assertArrayEquals(array2, imageContext.getPixelList().get(0).getY().getArray2DCopy());
      assertArrayEquals(array3, imageContext.getPixelList().get(0).getZ().getArray2DCopy());
    }
  }

  @Nested
  @DisplayName("Pre&Post Processing operations")
  public class PrePostProcessingOperations {

    OperationManager<ImageContext> testee;

    @BeforeEach
    void init() {
      testee = new OperationManager<ImageContext>() {
        @Override
        public List<Operation<ImageContext>> getOperations() {
          return Arrays.asList(
              new ImageProcessing.PreProcessingOperation(),
              new ImageProcessing.PostProcessingOperation());
        }
      };
    }

    @Test
    @DisplayName("complete, 4x4, normal image, rgb")
    void testProcess() {
      ImageContext imageContext = buildImageContext(
          TransformSetting.TransformationType.HAART_TRANSFORM,
          new HashMap<Integer, CompressionSetting>() {
            {
              put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
            }
          });

      double[][] array1 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 2.0, 3.0, 125.0},
          {3.0, 56.0, 3.0, 0.0},
          {3.0, 10.0, 102.0, 0.0}};
      double[][] array2 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 24.0, 3.0, 125.0},
          {3.0, 56.0, 0.0, 255.0},
          {34.0, 10.0, 251.0, 255.0}};
      double[][] array3 = new double[][]{
          {35.0, 122.0, 35.0, 123.0},
          {30.0, 200.0, 30.0, 125.0},
          {3.0, 56.0, 3.0, 255.0},
          {0.0, 100.0, 101.0, 23.0}};

      imageContext.setPixelList(Arrays.asList(
          new Vector<>(
              new Array2D(array1),
              new Array2D(array2),
              new Array2D(array3))
      ));

      testee.process(imageContext);

      assertArrayEquals(array1, imageContext.getPixelList().get(0).getX().getArray2DCopy());
      assertArrayEquals(array2, imageContext.getPixelList().get(0).getY().getArray2DCopy());
      assertArrayEquals(array3, imageContext.getPixelList().get(0).getZ().getArray2DCopy());
    }

    @Test
    @DisplayName("complete, 4x4, normal image, ycrcb")
    void testProcessYcrCb() {
      ImageContext imageContext = buildImageContext(
          TransformSetting.TransformationType.HAART_TRANSFORM,
          new HashMap<Integer, CompressionSetting>() {
            {
              put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
            }
          });
      imageContext.getResultSetting().getImageSetting()
          .setImageSchema(ImageSetting.ImageSchema.YCRCB);
      double[][] array1 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 2.0, 3.0, 125.0},
          {3.0, 56.0, 3.0, 0.0},
          {3.0, 10.0, 102.0, 0.0}};
      double[][] array2 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 24.0, 3.0, 125.0},
          {3.0, 56.0, 0.0, 255.0},
          {34.0, 10.0, 251.0, 255.0}};
      double[][] array3 = new double[][]{
          {35.0, 122.0, 35.0, 123.0},
          {30.0, 200.0, 30.0, 125.0},
          {3.0, 56.0, 3.0, 255.0},
          {0.0, 100.0, 101.0, 23.0}};

      imageContext.setPixelList(Arrays.asList(
          new Vector<>(
              new Array2D(array1),
              new Array2D(array2),
              new Array2D(array3))
      ));

      testee.process(imageContext);

      assertArrayEqualsWithDelta(array1, imageContext.getPixelList().get(0).getX().getArray2DCopy(),
          0.001);
      assertArrayEqualsWithDelta(array2, imageContext.getPixelList().get(0).getY().getArray2DCopy(),
          0.001);
      assertArrayEqualsWithDelta(array3, imageContext.getPixelList().get(0).getZ().getArray2DCopy(),
          0.001);
    }

    @Test
    @DisplayName("complete, 4x4, corrupted image, rgb")
    void testProcessCorrupted() {
      ImageContext imageContext = buildImageContext(
          TransformSetting.TransformationType.HAART_TRANSFORM,
          new HashMap<Integer, CompressionSetting>() {
            {
              put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
              put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
            }
          });
      double[][] array1 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 2.0, 3.0, 125.0},
          {3.0, 56.0, 3.0, -30.0},
          {3.0, 10.0, 102.0, 0.0}};
      double[][] array2 = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 24.0, 3.0, 125.0},
          {3.0, 56.0, -100.0, 285.0},
          {34.0, 10.0, 251.0, 255.0}};
      double[][] array3 = new double[][]{
          {35.0, 122.0, 35.0, 123.0},
          {30.0, 200.0, 30.0, 125.0},
          {3.0, 56.0, 3.0, 256.0},
          {-10.0, 100.0, 101.0, 23.0}};

      double[][] array1Correct = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 2.0, 3.0, 125.0},
          {3.0, 56.0, 3.0, 0.0},
          {3.0, 10.0, 102.0, 0.0}};
      double[][] array2Correct = new double[][]{
          {35.0, 122.0, 3.0, 123.0},
          {3.0, 24.0, 3.0, 125.0},
          {3.0, 56.0, 0.0, 255.0},
          {34.0, 10.0, 251.0, 255.0}};
      double[][] array3Correct = new double[][]{
          {35.0, 122.0, 35.0, 123.0},
          {30.0, 200.0, 30.0, 125.0},
          {3.0, 56.0, 3.0, 255.0},
          {0.0, 100.0, 101.0, 23.0}};

      imageContext.setPixelList(Arrays.asList(
          new Vector<>(
              new Array2D(array1),
              new Array2D(array2),
              new Array2D(array3))
      ));

      testee.process(imageContext);

      assertArrayEquals(array1Correct, imageContext.getPixelList().get(0).getX().getArray2DCopy());
      assertArrayEquals(array2Correct, imageContext.getPixelList().get(0).getY().getArray2DCopy());
      assertArrayEquals(array3Correct, imageContext.getPixelList().get(0).getZ().getArray2DCopy());
    }
  }
}