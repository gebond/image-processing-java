package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.context.ImageContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


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
    @DisplayName("complete, 8x8.png")
    void testProcess() throws IOException {
        ImageContext imageContext = ImageContext.startBuilder()
                .withImage(getImageUsingFileName("8x8.png"))
                .build();

        testee.process(imageContext);

        assertNotNull(imageContext);
    }

    private BufferedImage getImageUsingFileName(String imageName) throws IOException {
        return ImageIO.read(new File(getClass().getClassLoader().getResource("images/" + imageName).getFile()));
    }
}