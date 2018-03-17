package com.gebond.ip.view;

import com.gebond.ip.model.setting.CompressionSetting;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static gebond.ip.domain.manager.LogManager.log;

/**
 * Created on 28/02/18.
 */
public class MainForm extends JFrame {

    private static final int WIDTH = 650;
    private static final int HEIGHT = 500;

    protected JButton runButton;
    protected JSlider slider1;
    protected JSlider slider2;
    protected JSlider slider3;
    protected JPanel compressionPanel;
    protected JPanel submitPanel;
    protected JPanel transformationPanel;
    protected JPanel rightPanel;
    protected JPanel leftPanel;
    protected JPanel imagePanel;
    protected JPanel browserPanel;
    protected JPanel mainPanel;
    protected JComboBox<TransformSetting.TransformationType> selectMethodBox;
    protected JButton browserButton;
    protected JLabel imageLabel;
    protected JRadioButton rgbRadioButton;
    protected JRadioButton ycrcbRadioButton;
    protected JPanel fourierSettingPanel;
    protected JLabel selectMethod;
    protected JPanel discreteSettingPanel;
    protected JRadioButton sizeX8RadioButton;
    protected JRadioButton sizeX16RadioButton;
    protected JRadioButton sizeX32RadioButton;
    protected JTextField a3TextField;
    protected JButton cancelButton;
    protected JProgressBar progressBar1;
    protected JPanel emptyPanel;
    protected JPanel progressPanel;
    protected JPanel colorSchemaPanel;
    protected JLabel sliderValue1;
    protected JLabel sliderValue2;
    protected JLabel sliderValue3;
    protected JLabel sliderLabel1;
    protected JLabel sliderLabel2;
    protected JLabel sliderLabel3;
    protected JLabel totalValue;

    public MainForm() {
        super("Processing Form");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        log("mainForm initialized");
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

    public JLabel getImageabel() {
        return imageLabel;
    }

    public JRadioButton getRgbRadioButton() {
        return rgbRadioButton;
    }

    public JRadioButton getYcrcbRadioButton() {
        return ycrcbRadioButton;
    }

    public JPanel getFourierSettingPanel() {
        return fourierSettingPanel;
    }

    public JPanel getDiscreteSettingPanel() {
        return discreteSettingPanel;
    }

    public JLabel getSelectMethod() {
        return selectMethod;
    }

    public JRadioButton getSizeX8RadioButton() {
        return sizeX8RadioButton;
    }

    public JRadioButton getSizeX16RadioButton() {
        return sizeX16RadioButton;
    }

    public JRadioButton getSizeX32RadioButton() {
        return sizeX32RadioButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JPanel getProgressPanel() {
        return progressPanel;
    }

    public JLabel getSliderValue1() {
        return sliderValue1;
    }

    public JLabel getSliderValue2() {
        return sliderValue2;
    }

    public JLabel getSliderValue3() {
        return sliderValue3;
    }

    public JLabel getSliderLabel1() {
        return sliderLabel1;
    }

    public JLabel getSliderLabel2() {
        return sliderLabel2;
    }

    public JLabel getSliderLabel3() {
        return sliderLabel3;
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
        if (sizeX32RadioButton.isSelected()) {
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

    public JLabel getTotalValue() {
        return totalValue;
    }
}
