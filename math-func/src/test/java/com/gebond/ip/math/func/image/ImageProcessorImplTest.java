package com.gebond.ip.math.func.image;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import static com.gebond.ip.math.func.image.ImageTestHelper.assertImageEqualsWithSizes;
import static com.gebond.ip.math.func.image.ImageTestHelper.getImageUsingFileName;
import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;
import static com.gebond.ip.model.setting.ImageSetting.ImageSchema.RGB;
import static com.gebond.ip.model.setting.TransformSetting.TransformationType.HAART_TRANSFORM;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created on 02/03/18.
 */
@DisplayName("Image Processor")
class ImageProcessorImplTest {

    private ImageProcessor imageProcessor;

    @BeforeEach
    void setUp() {
        imageProcessor = new ImageProcessorImpl();
    }

    @DisplayName("processed fully, 8x8, rgb, x8, min")
    @Test
    void processTest() throws IOException {
        BufferedImage inputImage = getImageUsingFileName("8x8.png");

        BufferedImage resultImage = imageProcessor.processImage(
                ImageSetting.startBuilder()
                        .withImage(inputImage)
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .withSchema(RGB)
                        .withCompressions(new HashMap<Integer, CompressionSetting>() {
                            {
                                put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
                                put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
                                put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
                            }
                        })
                        .build(),
                TransformSetting.startBuilder()
                        .withType(HAART_TRANSFORM)
                        .build());

        assertImageEqualsWithSizes(inputImage, resultImage);
    }

    @DisplayName("processed fully, 64x64, rgb, x8, min")
    @Test
    void processTest_1() throws IOException {
        BufferedImage inputImage = getImageUsingFileName("64x64.png");

        BufferedImage resultImage = imageProcessor.processImage(
                ImageSetting.startBuilder()
                        .withImage(inputImage)
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .withSchema(RGB)
                        .withCompressions(new HashMap<Integer, CompressionSetting>() {
                            {
                                put(0, CompressionSetting.of(MIN_COMPRESSION_RATE));
                                put(1, CompressionSetting.of(MIN_COMPRESSION_RATE));
                                put(2, CompressionSetting.of(MIN_COMPRESSION_RATE));
                            }
                        })
                        .build(),
                TransformSetting.startBuilder()
                        .withType(HAART_TRANSFORM)
                        .build());

        assertImageEqualsWithSizes(inputImage, resultImage);
    }

    @DisplayName("processed, 8x8, rgb, x8, 50%")
    @Test
    void processTest_2() throws IOException {
        BufferedImage inputImage = getImageUsingFileName("64x64.png");

        BufferedImage resultImage = imageProcessor.processImage(
                ImageSetting.startBuilder()
                        .withImage(inputImage)
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .withSchema(RGB)
                        .withCompressions(new HashMap<Integer, CompressionSetting>() {
                            {
                                put(0, CompressionSetting.of(50.0));
                                put(1, CompressionSetting.of(50.0));
                                put(2, CompressionSetting.of(50.0));
                            }
                        })
                        .build(),
                TransformSetting.startBuilder()
                        .withType(HAART_TRANSFORM)
                        .build());

        assertNotNull(resultImage);
    }
}