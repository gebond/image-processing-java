package com.gebond.ip.view.component;

import com.gebond.ip.model.setting.ResultSetting;

import javax.swing.*;
import java.awt.image.BufferedImage;

import static com.gebond.ip.util.UIUtills.buildIconForDimension;

/**
 * Created on 06/03/18.
 */
public class ResultItem extends JLabel {

    private BufferedImage sourceImage;
    private ResultSetting resultSetting;

    public ResultItem(ResultSetting resultSetting) {
        super("Result#1");
        this.resultSetting = resultSetting;
        this.sourceImage = resultSetting.getImageSetting().getSourceImage();
        setText(resultSetting.getTransformSetting().getType().name());
        setIcon(buildIconForDimension(resultSetting.getResultImage(), 90, 90));
        System.out.println("New Result!");
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public ResultSetting getResultSetting() {
        return resultSetting;
    }

}
