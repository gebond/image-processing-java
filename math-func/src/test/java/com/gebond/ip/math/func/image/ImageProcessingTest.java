package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.context.ImageSetting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created on 17/02/18.
 */
@DisplayName("Image Operations")
public class ImageProcessingTest {

    private ImageProcessing testee = new ImageProcessing();

    @BeforeEach
    void init() {
    }

    @Test
    @DisplayName("complete, 8x8.png, x8")
    void testProcess_8x8() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("8x8.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(1, imageContext.getPixelList().size());
    }

    @Test
    @DisplayName("complete, 13x6.png, x8")
    void testProcess_13x6() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("13x6.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(0, imageContext.getPixelList().size());
    }

    @Test
    @DisplayName("complete, 9x12.png, x4")
    void testProcess_9x12() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("9x12.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X8)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(1, imageContext.getPixelList().size());
    }

    @Test
    @DisplayName("complete, 8x8.png, x4")
    void testProcess_8x8_4() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("8x8.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X4)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(4, imageContext.getPixelList().size());
    }

    @Test
    @DisplayName("complete, 13x6.png, x4")
    void testProcess_13x6_4() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("13x6.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X4)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(3, imageContext.getPixelList().size());
    }

    @Test
    @DisplayName("complete, 9x12.png, x4")
    void testProcess_9x12_4() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("9x12.png"))
                .withSetting(ImageSetting.startBuilder()
                        .withSegmentSize(ImageSetting.SegmentSize.X4)
                        .build())
                .build();

        testee.process(imageContext);

        assertEquals(6, imageContext.getPixelList().size());
    }

    private BufferedImage getImageUsingFileName(String imageName) throws IOException {
        return ImageIO.read(new File(getClass().getClassLoader().getResource("images/" + imageName).getFile()));
    }
}