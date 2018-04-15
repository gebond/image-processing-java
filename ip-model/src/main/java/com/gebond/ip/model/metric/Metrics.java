package com.gebond.ip.model.metric;

/**
 * Created on 22/03/18.
 */
public class Metrics {

  public enum MetricsType {
    MSE {
      @Override
      public String toString() {
        return "MSE";
      }
    },
    PSNR {
      @Override
      public String toString() {
        return "PSNR";
      }
    }
  }
}
