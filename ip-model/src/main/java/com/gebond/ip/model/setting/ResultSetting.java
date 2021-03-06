package com.gebond.ip.model.setting;

import com.gebond.ip.model.metric.Metrics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 11/03/18.
 */
public class ResultSetting {

  private BufferedImage resultImage;
  private long timeInMilles;
  private TransformSetting transformSetting;
  private ImageSetting imageSetting;
  private Map<Metrics.MetricsType, Map<ImageSetting.RGB, Double>> metrics;

  public ResultSetting() {
    metrics = new HashMap<>();
  }

  public static ResultSettingBuilder startBuilder() {
    return new ResultSettingBuilder();
  }

  public Map<Metrics.MetricsType, Map<ImageSetting.RGB, Double>> getMetrics() {
    return metrics;
  }

  public void setMetrics(Map<Metrics.MetricsType, Map<ImageSetting.RGB, Double>> metrics) {
    this.metrics = metrics;
  }

  public BufferedImage getResultImage() {
    return resultImage;
  }

  public void setResultImage(BufferedImage resultImage) {
    this.resultImage = resultImage;
  }

  public long getTimeInMilles() {
    return timeInMilles;
  }

  public void setTimeInMilles(long timeInMilles) {
    this.timeInMilles = timeInMilles;
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
}
