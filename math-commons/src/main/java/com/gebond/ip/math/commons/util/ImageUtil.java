package com.gebond.ip.math.commons.util;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created on 17/02/18.
 */
public class ImageUtil {
    /**
     * Converts a given Image into a BufferedImage
     *
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    /**
     * Normalize pixel array
     *
     * @param pixelArray input array of pixel values
     * @return normalized array
     */
    public static double[][] normalizePixelArray(double[][] pixelArray) {
        double[][] result = new double[pixelArray.length][pixelArray[0].length];
        for (int i = 0; i < pixelArray.length; i++) {
            for (int j = 0; j < pixelArray[0].length; j++) {
                double val = pixelArray[i][j];
                if (val < 0) {
                    result[i][j] = 0;
                    continue;
                }
                if (val > 255) {
                    result[i][j] = 255;
                    continue;
                }
                result[i][j] = pixelArray[i][j];
            }
        }
        return result;
    }

}
