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
        // todo maybe true false + exception
        @Override
        public void validate(ImageContext context) throws IllegalArgumentException {
            if (context.getImage() == null || context.getImageSetting() == null) {
                throw new IllegalArgumentException();
            }
            if (context.getImageSetting().getSegmentSize() == null) {
                throw new IllegalArgumentException();
            }
            if (context.getImage().getHeight() < context.getImageSetting().getSegmentSize().getValue()
                    || context.getImage().getWidth() < context.getImageSetting().getSegmentSize().getValue()) {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public void apply(ImageContext context) {
            BufferedImage bufferedImage = context.getImage();

            int size = context.getImageSetting().getSegmentSize().getValue();
            context.setRowCount(bufferedImage.getHeight() / size);
            context.setColumnCount(bufferedImage.getWidth() / size);

            for (int i = 0; i < context.getRowCount(); i++) {
                for (int j = 0; j < context.getColumnCount(); j++) {
                    context.getPixelList().add(buildVector(bufferedImage, i, j, size));
                }
            }
        }

        private Vector<Array2D> buildVector(BufferedImage bufferedImage, int row, int column, int size) {
            double[][] arrayX = new double[size][size];
            double[][] arrayY = new double[size][size];
            double[][] arrayZ = new double[size][size];
            // x -> column, y -> row
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Color color = new Color(bufferedImage.getRGB(x * column, y * row));
                    arrayX[x][y] = color.getRed();
                    arrayY[x][y] = color.getGreen();
                    arrayZ[x][y] = color.getBlue();
                }
            }
            Vector<Array2D> vector = new Vector<>();
            vector.setX(new Array2D(arrayX));
            vector.setY(new Array2D(arrayY));
            vector.setZ(new Array2D(arrayZ));
            return vector;
        }
    }

    public static class BuildingOperation implements Operation<ImageContext> {
        // todo maybe true false + exception
        @Override
        public void validate(ImageContext context) throws IllegalArgumentException {
            if (context.getImageSetting().getSegmentSize() == null) {
                throw new IllegalArgumentException();
            }
            if (context.getPixelList() == null || context.getPixelList().isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (context.getColumnCount() == 0 || context.getRowCount() == 0) {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public void apply(ImageContext context) {
            BufferedImage newImage = new BufferedImage(
                    context.getColumnCount() * context.getImageSetting().getSegmentSize().getValue(),
                    context.getRowCount() * context.getImageSetting().getSegmentSize().getValue(),
                    context.getImage().getType());

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
            double[][] arrayX = vector.getX().getArray2DCopy();
            double[][] arrayY = vector.getY().getArray2DCopy();
            double[][] arrayZ = vector.getZ().getArray2DCopy();
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    Color color = new Color(
                            (int) arrayX[x][y],
                            (int) arrayY[x][y],
                            (int) arrayZ[x][y]);
                    image.setRGB(startX * size + x, startY * size + y, color.getRGB());
                }
            }
        }
    }
}
