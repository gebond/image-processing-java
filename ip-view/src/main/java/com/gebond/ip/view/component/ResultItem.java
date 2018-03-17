package com.gebond.ip.view.component;

import com.gebond.ip.model.setting.ResultSetting;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created on 06/03/18.
 */
public class ResultItem extends JPanel {

    private BufferedImage sourceImage;
    private BufferedImage resultImage;


    public ResultItem(ResultSetting resultSetting) {
        this.resultImage = resultSetting.getResultImage();
        this.sourceImage = resultSetting.getImageSetting().getSourceImage();
    }
}
