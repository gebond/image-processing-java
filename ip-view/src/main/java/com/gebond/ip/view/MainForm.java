package com.gebond.ip.view;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 28/02/18.
 */
public class MainForm extends JFrame {

    private static final int WIDTH = 650;
    private static final int HEIGHT = 500;

    private JButton runButton;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JPanel compressionPanel;
    private JPanel submitPanel;
    private JPanel transformationPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JPanel imagePanel;
    private JPanel browserPanel;
    private JPanel mainPanel;
    private JComboBox<TransformSetting.TransformationType> selectMethodBox;
    private JButton browserButton;
    private JLabel image;
    private JRadioButton rgbRadioButton;
    private JRadioButton ycrcbRadioButton;
    private JPanel forierSettingPanel;
    private JLabel selectMethod;
    private JPanel descreteSettingPanel;
    private JRadioButton sizeX8RadioButton;
    private JRadioButton sizeX16RadioButton;
    private JRadioButton sizeX32RadioButton;

    public MainForm() {
        super("Processing Form");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }

    public JButton getRunButton() {
        return runButton;
    }

    public JSlider getSlider1() {
        return slider1;
    }

    public JSlider getSlider2() {
        return slider2;
    }

    public JSlider getSlider3() {
        return slider3;
    }

    public JPanel getCompressionPanel() {
        return compressionPanel;
    }

    public JPanel getSubmitPanel() {
        return submitPanel;
    }

    public JPanel getTransformationPanel() {
        return transformationPanel;
    }

    public JPanel getRightPanel() {
        return rightPanel;
    }

    public JPanel getLeftPanel() {
        return leftPanel;
    }

    public JPanel getImagePanel() {
        return imagePanel;
    }

    public JPanel getBrowserPanel() {
        return browserPanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox<TransformSetting.TransformationType> getSelectMethodBox() {
        return selectMethodBox;
    }

    public JButton getBrowserButton() {
        return browserButton;
    }

    public JLabel getImage() {
        return image;
    }

    public ImageSetting buildImageSetting(BufferedImage lastImage) {
        return ImageSetting.startBuilder()
                .withImage(lastImage)
                .withSegmentSize(getSegmentSize())
                .withSchema(buildSchema())
                .withCompressions(buildCompressions())
                .build();
    }

    public TransformSetting buildTransformSetting() {
        return TransformSetting.startBuilder()
                .withType((TransformSetting.TransformationType) selectMethodBox.getSelectedItem())
                .build();
    }

    private ImageSetting.SegmentSize getSegmentSize() {
        if (sizeX8RadioButton.isSelected()) {
            return ImageSetting.SegmentSize.X8;
        }
        if (sizeX16RadioButton.isSelected()) {
            return ImageSetting.SegmentSize.X16;
        }
        if (sizeX8RadioButton.isSelected()) {
            return ImageSetting.SegmentSize.X32;
        }
        return ImageSetting.SegmentSize.X8;
    }

    private Map<Integer, CompressionSetting> buildCompressions() {
        Map<Integer, CompressionSetting> compressionSettingMap = new HashMap<>();
        compressionSettingMap.put(ImageSetting.RGB.RED.getOrder(), CompressionSetting.of(slider1.getValue()));
        compressionSettingMap.put(ImageSetting.RGB.GREEN.getOrder(), CompressionSetting.of(slider2.getValue()));
        compressionSettingMap.put(ImageSetting.RGB.BLUE.getOrder(), CompressionSetting.of(slider3.getValue()));
        return compressionSettingMap;
    }

    private ImageSetting.ImageSchema buildSchema() {
        if (rgbRadioButton.isSelected()) {
            return ImageSetting.ImageSchema.RGB;
        }
        if (ycrcbRadioButton.isSelected()) {
            return ImageSetting.ImageSchema.YCRCB;
        }
        return ImageSetting.ImageSchema.RGB;
    }
}
