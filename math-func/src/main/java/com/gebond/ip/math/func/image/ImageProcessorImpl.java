package com.gebond.ip.math.func.image;

import com.gebond.ip.math.func.context.ImageContext;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;

import java.awt.image.BufferedImage;

/**
 * Created on 02/03/18.
 */
public class ImageProcessorImpl implements ImageProcessor {

    private ImageProcessing imageProcessing;

    public ImageProcessorImpl() {
        imageProcessing = new ImageProcessing();
    }

    @Override
    public BufferedImage processImage(ImageSetting imageSetting,
                                      TransformSetting transformSetting) {
        return imageProcessing.process(
                ImageContext.startBuilder()
                        .withSetting(imageSetting)
                        .withSetting(transformSetting)
                        .build())
                .getImage();
    }
}
