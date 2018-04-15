package com.gebond.ip.util;

import com.gebond.ip.math.func.image.ImageProcessor;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.model.setting.TransformSetting;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * Created on 15/04/18.
 */
public class ProcessingUtil {

  private ProcessingUtil() {
  }

  public static CompletableFuture<ResultSetting> retrieveFuture(ImageProcessor imageProcessor,
      ImageSetting imageSetting,
      TransformSetting transformSetting) {
    CompletableFuture<ResultSetting> completableFuture = new CompletableFuture<>();
    Executors.newCachedThreadPool().submit(() -> {
      completableFuture.complete(imageProcessor.processImage(imageSetting, transformSetting));
      return null;
    });
    return completableFuture;
  }
}
