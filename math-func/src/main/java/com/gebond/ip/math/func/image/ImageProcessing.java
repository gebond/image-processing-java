package com.gebond.ip.math.func.image;

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
        }

        @Override
        public void apply(ImageContext context) {
            BufferedImage bufferedImage = context.getImage();

            int size = context.getImageSetting().getSegmentSize().getValue();
            context.setRowCount(bufferedImage.getHeight() / size);
            context.setColumnCount(bufferedImage.getWidth() / size);



            for (int i = 0; i < bufferedImage.getHeight(); i++) {
                for (int j = 0; j < bufferedImage.getWidth(); j++) {
                    bufferedImage.getRGB(0, 0);
                }
            }
            new Color(bufferedImage.getRGB(0, 0));

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

        }
    }
}
