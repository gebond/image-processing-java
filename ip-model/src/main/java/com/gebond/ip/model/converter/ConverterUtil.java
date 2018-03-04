package com.gebond.ip.model.converter;

import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.Vector;

/**
 * Created on 04/03/18.
 */
public class ConverterUtil {

    /**
     * Convert RGB pixel array to YCrCb
     *
     * @param rgbVector input array of RGB pixel values
     */
    public static void converRGBToYCrCb(Vector<Array2D> rgbVector) {
        double[][] redColor = rgbVector.getX().getArray2DCopy();
        double[][] greenColor = rgbVector.getY().getArray2DCopy();
        double[][] blueColor = rgbVector.getZ().getArray2DCopy();
        double[][] yColor = new double[redColor.length][redColor[0].length];
        double[][] crColor = new double[greenColor.length][greenColor[0].length];
        double[][] cbColor = new double[blueColor.length][blueColor[0].length];
        for (int i = 0; i < redColor.length; i++) {
            for (int j = 0; j < redColor[0].length; j++) {
                yColor[i][j] = (0.299 * redColor[i][j]) + (0.587 * greenColor[i][j]) + (0.114 * blueColor[i][j]);
                cbColor[i][j] = 128 - (0.168736 * redColor[i][j]) - (0.331264 * greenColor[i][j]) + (0.5 * blueColor[i][j]);
                crColor[i][j] = 128 + (0.5 * redColor[i][j]) - (0.418688 * greenColor[i][j]) - (0.081312 * blueColor[i][j]);
            }
        }
        rgbVector.setX(Array2D.of(yColor));
        rgbVector.setY(Array2D.of(crColor));
        rgbVector.setZ(Array2D.of(cbColor));
    }

    /**
     * Convert YCrCb pixel array to RGB
     *
     * @param yCrCbVector input array of YCrCb pixel values
     */
    public static void converYCrCbToRGB(Vector<Array2D> yCrCbVector) {
        double[][] yColor = yCrCbVector.getX().getArray2DCopy();
        double[][] crColor = yCrCbVector.getY().getArray2DCopy();
        double[][] cbColor = yCrCbVector.getZ().getArray2DCopy();
        double[][] redColor = new double[yColor.length][yColor[0].length];
        double[][] greenColor = new double[redColor.length][redColor[0].length];
        double[][] blueColor = new double[redColor.length][redColor[0].length];
        for (int i = 0; i < redColor.length; i++) {
            for (int j = 0; j < redColor[0].length; j++) {
                redColor[i][j] = yColor[i][j] + 1.402 * (crColor[i][j] - 128.0);
                greenColor[i][j] = yColor[i][j] - 0.34414 * (cbColor[i][j] - 128.0) - 0.71414 * (crColor[i][j] - 128.0);
                blueColor[i][j] = yColor[i][j] + 1.772 * (cbColor[i][j] - 128.0);
            }
        }
        yCrCbVector.setX(Array2D.of(redColor));
        yCrCbVector.setY(Array2D.of(greenColor));
        yCrCbVector.setZ(Array2D.of(blueColor));
    }
}
