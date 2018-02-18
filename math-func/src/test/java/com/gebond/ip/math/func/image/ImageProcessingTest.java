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
        BufferedImage bufferedImage = getImageUsingFileName("8x8.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X8);

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
        assertEquals(bufferedImage.getRGB(0, 0), imageContext.getImage().getRGB(0, 0));
        assertEquals(bufferedImage.getRGB(7, 7), imageContext.getImage().getRGB(7, 7));
        assertEquals(bufferedImage.getRGB(0, 7), imageContext.getImage().getRGB(0, 7));
        assertEquals(bufferedImage.getRGB(7, 0), imageContext.getImage().getRGB(7, 0));
    }

    @Test
    @DisplayName("complete, 9x12.png, x8")
    void testProcess_9x12() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("9x12.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X8);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(1, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "9x12");
    }

    @Test
    @DisplayName("complete, 13x6.png, x8")
    void testProcess_13x6() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("13x6.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X8);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(0, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "13x6");
    }

    @Test
    @DisplayName("complete, 8x16.png, x8")
    void testProcess_8x16() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("8x16.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X8);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(2, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "8x16");
    }

    @Test
    @DisplayName("complete, 16x8.png, x8")
    void testProcess_16x8() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("16x8.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X8);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(2, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "16x8");
    }

    @Test
    @DisplayName("complete, 8x8.png, x4")
    void testProcess_8x8_4() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("8x8.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X4);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(4, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "8x8_x4");
    }

    @Test
    @DisplayName("complete, 9x12.png, x4")
    void testProcess_9x12_4() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("9x12.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X4);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(6, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "9x12_x4");
    }

    @Test
    @DisplayName("complete, 13x6.png, x4")
    void testProcess_13x6_4() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("13x6.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X4);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(3, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "13x6_x4");
    }

    @Test
    @DisplayName("complete, 8x16.png, x4")
    void testProcess_8x16_x4() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("8x16.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X4);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(8, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "8x16_x4");
    }

    @Test
    @DisplayName("complete, 16x8.png, x4")
    void testProcess_16x8_x4() throws IOException {
        BufferedImage bufferedImage = getImageUsingFileName("16x8.png");
        ImageContext imageContext = buildImageContext(bufferedImage, ImageSetting.SegmentSize.X4);

        testee.process(imageContext);

        assertEquals(imageContext.getColumnCount() * imageContext.getRowCount(), imageContext.getPixelList().size());
        assertEquals(8, imageContext.getPixelList().size());

        saveImage(imageContext.getImage(), "16x8_x4");
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