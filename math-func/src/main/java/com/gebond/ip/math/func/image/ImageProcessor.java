package com.gebond.ip.math.func.image;

import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.model.setting.TransformSetting;

/**
 * Created on 02/03/18.
 */
public interface ImageProcessor {

  ResultSetting processImage(ImageSetting imageSetting,
      TransformSetting transformSetting);
}
