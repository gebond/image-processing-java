package com.gebond.ip.view.component;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import static com.gebond.ip.model.metric.Metrics.MetricsType.MSE;
import static com.gebond.ip.model.metric.Metrics.MetricsType.PSNR;
import static com.gebond.ip.util.UIUtills.buildIconForDimension;

/**
 * Created on 06/03/18.
 */
public class ResultItem extends JLabel {

    private BufferedImage sourceImage;
    private ResultSetting resultSetting;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public ResultItem(ResultSetting resultSetting) {
        super("Result#1");
        this.resultSetting = resultSetting;
        this.sourceImage = resultSetting.getImageSetting().getSourceImage();
        setText(buildDescription(resultSetting));
        setIcon(buildIconForDimension(resultSetting.getResultImage(), 90, 90));
        System.out.println("New Result!");
    }

    private String buildDescription(ResultSetting resultSetting) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("Method: " + resultSetting.getTransformSetting().getType().name() + "<br/>");
        stringBuilder.append("Schema: " + resultSetting.getImageSetting().getImageSchema().toString() + "<br/>");
        stringBuilder.append("Compression: " + df2.format(resultSetting.getImageSetting().getImageValues()
                .values().stream()
                .mapToDouble(CompressionSetting::getCompressionRate)
                .average().getAsDouble()) + "%<br/>");
        stringBuilder.append("Time: " + df2.format(resultSetting.getTimeInMilles() / 1000000000.0) + "s<br/>");
        stringBuilder.append(resultSetting.getMetrics().get(MSE) != null ? "MSE: [" +
                df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.RED)) + ", " +
                df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.GREEN)) + ", " +
                df2.format(resultSetting.getMetrics().get(MSE).get(ImageSetting.RGB.BLUE)) + "]<br/>"
                : "");
        stringBuilder.append(resultSetting.getMetrics().get(PSNR) != null ? "PSNR: [" +
                df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.RED)) + ", " +
                df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.GREEN)) + ", " +
                df2.format(resultSetting.getMetrics().get(PSNR).get(ImageSetting.RGB.BLUE)) + "]<br/>"
                : "");
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }
}
