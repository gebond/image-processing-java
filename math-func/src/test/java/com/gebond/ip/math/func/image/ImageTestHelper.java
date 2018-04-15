package com.gebond.ip.math.func.image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created on 02/03/18.
 */
public class ImageTestHelper {

  public static BufferedImage getImageUsingFileName(String imageName) throws IOException {
    return ImageIO.read(new File(
        ImageTestHelper.class.getClassLoader().getResource("images/" + imageName).getFile()));
  }

  public static void assertImageEquals(BufferedImage expected, BufferedImage actual) {
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    for (int x = 0; x < actual.getWidth(); x++) {
      for (int y = 0; y < actual.getHeight(); y++) {
        assertEquals(expected.getRGB(x, y), actual.getRGB(x, y));
      }
    }
  }

  public static void assertImageEqualsWithSizes(BufferedImage expected, BufferedImage actual) {
    assertEquals(expected.getWidth(), actual.getWidth());
    assertEquals(expected.getHeight(), actual.getHeight());
    assertImageEquals(expected, actual);
  }
}
