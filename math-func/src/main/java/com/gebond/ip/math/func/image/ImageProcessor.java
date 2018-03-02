package com.gebond.ip.math.func.image;

import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;

import java.awt.image.BufferedImage;

/**
 * Created on 02/03/18.
 */
public interface ImageProcessor {
    BufferedImage processImage(ImageSetting imageSetting,
                               TransformSetting transformSetting);
}
