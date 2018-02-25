package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.array.Array2D;
import com.gebond.ip.math.func.array.Vector;
import com.gebond.ip.math.func.compression.CompressionSetting;
import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.context.ImageSetting;
import com.gebond.ip.math.func.context.TransformSetting;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created on 17/02/18.
 */
@DisplayName("Image Operations")
public class ImageProcessingTest {

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
                    imageContext.getColumnCount() * imageContext.getImageSetting().getSegmentSize().getValue(),
                    imageContext.getImage().getWidth());
            assertEquals(
                    imageContext.getRowCount() * imageContext.getImageSetting().getSegmentSize().getValue(),
                    imageContext.getImage().getHeight());
            saveImage(imageContext.getImage(), "8x8");
            assertEquals(input.getRGB(0, 0), imageContext.getImage().getRGB(0, 0));
            assertEquals(input.getRGB(7, 7), imageContext.getImage().getRGB(7, 7));
            assertEquals(input.getRGB(0, 7), imageContext.getImage().getRGB(0, 7));
            assertEquals(input.getRGB(7, 7), imageContext.getImage().getRGB(7, 7));
            assertEquals(input.getRGB(3, 6), imageContext.getImage().getRGB(3, 6));
        }

        @Test
        @DisplayName("complete, 9x12.png, x8")
        void testProcess_9x12() throws IOException {
            BufferedImage input = getImageUsingFileName("9x12.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
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

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(2, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "8x16");
        }

        @Test
        @DisplayName("complete, 16x8.png, x8")
        void testProcess_16x8() throws IOException {
            BufferedImage input = getImageUsingFileName("16x8.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(2, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "16x8");
        }

        @Test
        @DisplayName("complete, 64x64.png, x8")
        void testProcess_64x64() throws IOException {
            BufferedImage input = getImageUsingFileName("64x64.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X8);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(64, imageContext.getPixelList().size());


            assertEquals(input.getRGB(10, 10), imageContext.getImage().getRGB(10, 10));
            assertEquals(input.getRGB(35, 15), imageContext.getImage().getRGB(35, 15));
            assertEquals(input.getRGB(31, 31), imageContext.getImage().getRGB(31, 31));
            assertEquals(input.getRGB(15, 35), imageContext.getImage().getRGB(15, 35));
            assertEquals(input.getRGB(55, 55), imageContext.getImage().getRGB(55, 55));

            saveImage(imageContext.getImage(), "64x64");
        }

        @Test
        @DisplayName("complete, 6x5.png, x4")
        void testProcess_6x5_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("6x5.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
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

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(4, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "8x8_x4");

            assertEquals(input.getRGB(0, 0), imageContext.getImage().getRGB(0, 0));
            assertEquals(input.getRGB(7, 7), imageContext.getImage().getRGB(7, 7));
            assertEquals(input.getRGB(0, 7), imageContext.getImage().getRGB(0, 7));
            assertEquals(input.getRGB(7, 7), imageContext.getImage().getRGB(7, 7));
            assertEquals(input.getRGB(3, 6), imageContext.getImage().getRGB(3, 6));
        }

        @Test
        @DisplayName("complete, 6x10.png, x4")
        void testProcess_6x10_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("6x10.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(2, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "6x10_x4");
        }

        @Test
        @DisplayName("complete, 9x12.png, x4")
        void testProcess_9x12_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("9x12.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(6, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "9x12_x4");
        }

        @Test
        @DisplayName("complete, 13x6.png, x4")
        void testProcess_13x6_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("13x6.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(3, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "13x6_x4");
        }

        @Test
        @DisplayName("complete, 8x16.png, x4")
        void testProcess_8x16_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("8x16.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(8, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "8x16_x4");
        }

        @Test
        @DisplayName("complete, 16x8.png, x4")
        void testProcess_16x8_x4() throws IOException {
            BufferedImage input = getImageUsingFileName("16x8.png");
            ImageContext imageContext = buildImageContext(input, ImageSetting.SegmentSize.X4);

            testee.process(imageContext);

            assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
            assertEquals(8, imageContext.getPixelList().size());

            saveImage(imageContext.getImage(), "16x8_x4");
        }
    }

    @Nested
    @DisplayName("Consistent operations")
    public class ConsistentOperations {

        OperationManager<ImageContext> testee;

        @BeforeEach
        void init() {
            testee = new OperationManager<ImageContext>() {
                @Override
                public List<Operation<ImageContext>> getOperations() {
                    return Arrays.asList(
                            new ImageProcessing.ConsistentOperation());
                }
            };
        }

        @Test
        @DisplayName("complete, 2x2, haart, min")
        void testProcess() {
            ImageContext imageContext = buildImageContext(
                    TransformSetting.TransformationType.HAART_TRANSFORM,
                    CompressionSetting.MIN_COMPRESSION_RATE);

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
                    CompressionSetting.MIN_COMPRESSION_RATE);

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

    private ImageContext buildImageContext(TransformSetting.TransformationType transformationType, double compressionRate) {
        return ImageContext.startBuilder()
                .withSetting(TransformSetting.startBuilder()
                        .withType(transformationType)
                        .withCompression(CompressionSetting.of(compressionRate))
                        .build())
                .build();
    }

    private ImageContext buildImageContext(BufferedImage bufferedImage, ImageSetting.SegmentSize segmentSize) {
        return ImageContext.startBuilder()
                .withImage(bufferedImage)
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(segmentSize)
                        .build())
                .build();
    }

    private BufferedImage getImageUsingFileName(String imageName) throws IOException {
        return ImageIO.read(new File(getClass().getClassLoader().getResource("images/" + imageName).getFile()));
    }

    private void saveImage(BufferedImage image, String imageName) throws IOException {
        File outputfile = new File("target/OUT-" + imageName + ".png");
        ImageIO.write(image, "png", outputfile);
    }
}