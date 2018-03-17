package com.gebond.ip.model.setting;

import java.awt.image.BufferedImage;

/**
 * Created on 11/03/18.
 */
public class ResultSetting {
    private BufferedImage resultImage;
    private long timeInMilSec;
    private TransformSetting transformSetting;
    private ImageSetting imageSetting;

    public static ResultSettingBuilder startBuilder() {
        return new ResultSettingBuilder();
    }

    public static class ResultSettingBuilder {
        private ResultSetting resultSetting;

        public ResultSettingBuilder() {
            resultSetting = new ResultSetting();
        }

        public ResultSettingBuilder withResultImage(BufferedImage resultImage) {
            resultSetting.resultImage = resultImage;
            return this;
        }

        public ResultSettingBuilder withImageSetting(ImageSetting imageSetting) {
            resultSetting.imageSetting = imageSetting;
            return this;
        }

        public ResultSettingBuilder withTransformSetting(TransformSetting transformSetting) {
            resultSetting.transformSetting = transformSetting;
            return this;
        }

        public ResultSetting build() {
            return resultSetting;
        }
    }

    public BufferedImage getResultImage() {
        return resultImage;
    }

    public void setResultImage(BufferedImage resultImage) {
        this.resultImage = resultImage;
    }

    public long getTimeInMilSec() {
        return timeInMilSec;
    }

    public void setTimeInMilSec(long timeInMilSec) {
        this.timeInMilSec = timeInMilSec;
    }

    public TransformSetting getTransformSetting() {
        return transformSetting;
    }

    public void setTransformSetting(TransformSetting transformSetting) {
        this.transformSetting = transformSetting;
    }

    public ImageSetting getImageSetting() {
        return imageSetting;
    }

    public void setImageSetting(ImageSetting imageSetting) {
        this.imageSetting = imageSetting;
    }
}
