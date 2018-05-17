package com.gebond.ip.view.component;

import static com.gebond.ip.model.metric.Metrics.MetricsType.MSE;
import static com.gebond.ip.model.metric.Metrics.MetricsType.PSNR;
import static com.gebond.ip.util.ImageIconUtil.buildIconForDimension;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import javax.swing.JLabel;

/**
 * Created on 06/03/18.
 */
public class ResultItem extends JLabel {

  private static DecimalFormat df2 = new DecimalFormat("#.##");
  private BufferedImage sourceImage;
  private ResultSetting resultSetting;

  public ResultItem(ResultSetting resultSetting) {
    super("Result#1");
    this.resultSetting = resultSetting;
    this.sourceImage = resultSetting.getImageSetting().getSourceImage();
    setText(buildDescription(resultSetting));
    setIcon(buildIconForDimension(resultSetting.getResultImage(), 90, 90));
    System.out.println("New Result!");
  }

  private String buildDescription(ResultSetting resultSetting) {
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("<html>");
    strBuilder.append("Method: " + resultSetting.getTransformSetting().getType().name() + "<br/>");
    strBuilder
        .append("Schema: " + resultSetting.getImageSetting().getImageSchema().toString() + "<br/>");
    strBuilder.append("Compression: " + df2.format(resultSetting.getImageSetting().getCompressionValues()
        .values().stream()
        .mapToDouble(CompressionSetting::getCompressionRate)
        .average().getAsDouble()) + "%<br/>");
    strBuilder
        .append("Time: " + df2.format(resultSetting.getTimeInMilles() / 1000000000.0) + "s<br/>");
    strBuilder.append(resultSetting.getMetrics().get(MSE) != null ? "MSE: [" +
        df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.RED)) + ", " +
        df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.GREEN)) + ", " +
        df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.BLUE)) + "]<br/>"
        : "");
    strBuilder.append(resultSetting.getMetrics().get(PSNR) != null ? "PSNR: [" +
        df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.RED)) + ", " +
        df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.GREEN)) + ", " +
        df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.BLUE)) + "]<br/>"
        : "");
    strBuilder.append("</html>");
    return strBuilder.toString();
  }
}
