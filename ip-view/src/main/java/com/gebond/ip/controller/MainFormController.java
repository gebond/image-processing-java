package com.gebond.ip.controller;

import com.gebond.ip.math.func.image.ImageProcessor;
import com.gebond.ip.math.func.image.ImageProcessorImpl;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.TransformSetting;
import com.gebond.ip.view.MainForm;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created on 28/02/18.
 */
public class MainFormController {

    private static final String PNG = "png";
    private static final String JPG = "jpg";

    MainForm mainForm;
    //left panel
    private JButton browserButton;
    private JLabel imageLabel;
    //right panel
    private JButton runButton;
    private JComboBox<TransformSetting.TransformationType> selectMethodBox;
    private JRadioButton rgbButton;
    private JRadioButton ycrcbButton;
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JLabel sliderLabel1;
    private JLabel sliderLabel2;
    private JLabel sliderLabel3;
    private JLabel sliderValue1;
    private JLabel sliderValue2;
    private JLabel sliderValue3;
    private JButton cancelButton;
    //util
    private JFileChooser fileChooser;
    // in-memory input image representation
    private BufferedImage lastImage;

    private ResultsFormController resultsFormController;
    private ImageProcessor imageProcessor;
    private ImageSetting imageSetting;
    private TransformSetting transformSetting;
    private BufferedImage resultImage;

    private Thread processingTread;

    public MainFormController(ResultsFormController resultsFormController) {
        this.resultsFormController = resultsFormController;
        imageProcessor = new ImageProcessorImpl();
        initComponents();
        adjustComponents();
        initListeners();
    }

    public void show() {
        mainForm.setVisible(true);
    }

    private void initComponents() {
        mainForm = new MainForm();
        browserButton = mainForm.getBrowserButton();
        imageLabel = mainForm.getImage();
        runButton = mainForm.getRunButton();
        selectMethodBox = mainForm.getSelectMethodBox();
        rgbButton = mainForm.getRgbRadioButton();
        ycrcbButton = mainForm.getYcrcbRadioButton();
        slider1 = mainForm.getSlider1();
        slider2 = mainForm.getSlider2();
        slider3 = mainForm.getSlider3();
        sliderValue1 = mainForm.getSliderValue1();
        sliderValue2 = mainForm.getSliderValue2();
        sliderValue3 = mainForm.getSliderValue3();
        sliderLabel1 = mainForm.getSliderLabel1();
        sliderLabel2 = mainForm.getSliderLabel2();
        sliderLabel3 = mainForm.getSliderLabel3();
        cancelButton = mainForm.getCancelButton();
    }

    void adjustComponents() {
        fileChooser = configureJFileChooser();
        for (TransformSetting.TransformationType transformationType : TransformSetting.TransformationType.values()) {
            selectMethodBox.addItem(transformationType);
        }
        sliderValue1.setText(slider1.getValue() + "%");
        sliderValue2.setText(slider2.getValue() + "%");
        sliderValue3.setText(slider3.getValue() + "%");
        sliderLabel1.setText(ImageSetting.RGB.RED.toString());
        sliderLabel2.setText(ImageSetting.RGB.GREEN.toString());
        sliderLabel3.setText(ImageSetting.RGB.BLUE.toString());
    }

    void initListeners() {
        // browse image file and set background
        browserButton.addActionListener(e -> {
            int result = fileChooser.showDialog(null,
                    "Select");
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    lastImage = ImageIO.read(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            updateImage(lastImage);
            runButton.setEnabled(true);
        });

        // run processing
        runButton.addActionListener(e -> {
            try {
                imageSetting = mainForm.buildImageSetting(lastImage);
                transformSetting = mainForm.buildTransformSetting();
                processingTread = new Thread(() ->
                        updateImage(imageProcessor.processImage(imageSetting, transformSetting)));
                processingTread.start();
                cancelButton.setEnabled(true);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(mainForm,
                        ex.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // change RGB/YCrCb schema
        rgbButton.addActionListener(e -> {
            sliderLabel1.setText(ImageSetting.RGB.RED.toString());
            sliderLabel2.setText(ImageSetting.RGB.GREEN.toString());
            sliderLabel3.setText(ImageSetting.RGB.BLUE.toString());
        });
        ycrcbButton.addActionListener(e -> {
            sliderLabel1.setText(ImageSetting.YCRCB.Y.toString());
            sliderLabel2.setText(ImageSetting.YCRCB.CR.toString());
            sliderLabel3.setText(ImageSetting.YCRCB.CB.toString());
        });

        // change sliders event
        slider1.addChangeListener(evt -> {
            sliderValue1.setText(slider1.getValue() + "%");
        });
        slider2.addChangeListener(evt -> {
            sliderValue2.setText(slider2.getValue() + "%");
        });
        slider3.addChangeListener(evt -> {
            sliderValue3.setText(slider3.getValue() + "%");
        });

        // cancel button
        cancelButton.addActionListener(e -> {
            processingTread.interrupt();
            cancelButton.setEnabled(false);
        });
    }

    private void updateImage(BufferedImage image) {
        Image dimg = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(dimg));
    }

    private static JFileChooser configureJFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                String extension = FilenameUtils.getExtension(f.getName());
                return extension.equals(PNG) || extension.equals(JPG);
            }

            @Override
            public String getDescription() {
                return null;
            }
        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }
}
