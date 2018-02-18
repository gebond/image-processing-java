package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.array.Array2D;
import com.gebond.ip.math.func.array.Vector;
import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 17/02/18.
 */
public class ImageProcessing extends OperationManager<ImageContext> {
    @Override
    public List<Operation<ImageContext>> getOperations() {
        return Arrays.asList(
                new SplittingOperation(),
                new BuildingOperation()
        );
    }

    public static class SplittingOperation implements Operation<ImageContext> {
        @Override
        public boolean validate(ImageContext context) throws IllegalArgumentException {
            if (context.getImage() == null || context.getImageSetting() == null) {
                throw new IllegalArgumentException("Image or setting is null");
            }
            if (context.getImageSetting().getSegmentSize() == null) {
                throw new IllegalArgumentException("Segment size is null");
            }
            if (context.getImage().getHeight() < context.getImageSetting().getSegmentSize().getValue()
                    || context.getImage().getWidth() < context.getImageSetting().getSegmentSize().getValue()) {
                throw new IllegalArgumentException("Height or Width must be more than segment size");
            }
            return true;
        }

        @Override
        public void apply(ImageContext context) {
            BufferedImage bufferedImage = context.getImage();

            int size = context.getImageSetting().getSegmentSize().getValue();
            context.setRowCount(bufferedImage.getHeight() / size);
            context.setColumnCount(bufferedImage.getWidth() / size);

            for (int i = 0; i < context.getRowCount(); i++) {
                for (int j = 0; j < context.getColumnCount(); j++) {
                    context.getPixelList().add(buildVector(bufferedImage, i, j, context.getRowCount(), context.getColumnCount(), size));
                }
            }
        }

        private Vector<Array2D> buildVector(BufferedImage bufferedImage, int rowCurrent, int columnCurrent, int rowMax, int columnMax, int size) {
            double[][] arrayReds = new double[size][size];
            double[][] arrayGreens = new double[size][size];
            double[][] arrayBlues = new double[size][size];
            // x -> column, y -> row
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Color color = new Color(bufferedImage.getRGB(columnMax * columnCurrent + x, rowMax * rowCurrent + y));
                    arrayReds[x][y] = color.getRed();
                    arrayGreens[x][y] = color.getGreen();
                    arrayBlues[x][y] = color.getBlue();
                }
            }
            Vector<Array2D> vector = new Vector<>(
                    new Array2D(arrayReds),
                    new Array2D(arrayGreens),
                    new Array2D(arrayBlues));
            return vector;
        }
    }

    public static class BuildingOperation implements Operation<ImageContext> {
        @Override
        public boolean validate(ImageContext context) throws IllegalArgumentException {
            if (context.getImageSetting().getSegmentSize() == null) {
                throw new IllegalArgumentException("Image setting segment is null");
            }
            if (context.getPixelList() == null || context.getPixelList().isEmpty()) {
                throw new IllegalArgumentException("List of pixel vector arrays is null or empty");
            }
            if (context.getColumnCount() == 0 || context.getRowCount() == 0) {
                throw new IllegalArgumentException("Column/Row count is null");
            }
            return true;
        }

        @Override
        public void apply(ImageContext context) {
            BufferedImage newImage = new BufferedImage(
                    context.getColumnCount() * context.getImageSetting().getSegmentSize().getValue(),
                    context.getRowCount() * context.getImageSetting().getSegmentSize().getValue(),
                    BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < context.getRowCount(); i++) {
                for (int j = 0; j < context.getColumnCount(); j++) {
                    applyVector(newImage,
                            context.getPixelList().get(i * context.getColumnCount() + j),
                            j,
                            i,
                            context.getImageSetting().getSegmentSize().getValue());
                }
            }
            context.setImage(newImage);
        }

        private void applyVector(BufferedImage image, Vector<Array2D> vector, int startX, int startY, int size) {
            double[][] arrayReds = vector.getX().getArray2DCopy();
            double[][] arrayGreens = vector.getY().getArray2DCopy();
            double[][] arrayBlues = vector.getZ().getArray2DCopy();
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Color color = new Color(
                            (int) arrayReds[x][y],
                            (int) arrayGreens[x][y],
                            (int) arrayBlues[x][y]);
                    image.setRGB(startX * size + x, startY * size + y, color.getRGB());
                }
            }
        }
    }
}
