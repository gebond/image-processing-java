package com.gebond.ip.controller;

import com.gebond.ip.math.func.image.ImageProcessor;
import com.gebond.ip.math.func.image.ImageProcessorImpl;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
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

import static com.gebond.ip.util.UIUtills.buildIconForLabel;

/**
 * Created on 28/02/18.
 */
public class MainFormController extends MainForm {

    private static final String PNG = "png";
    private static final String JPG = "jpg";
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
        super();
        this.resultsFormController = resultsFormController;
        imageProcessor = new ImageProcessorImpl();
        adjustComponents();
        initListeners();
    }

    public void showUp() {
        super.setVisible(true);
    }

    void adjustComponents() {
        fileChooser = configureJFileChooser();
        for (TransformSetting.TransformationType transformationType : TransformSetting.TransformationType.values()) {
            selectMethodBox.addItem(transformationType);
        }
        sliderValue1.setText(slider1.getValue() + "%");
        sliderValue2.setText(slider2.getValue() + "%");
        sliderValue3.setText(slider3.getValue() + "%");
        updateTotalValue();
        sliderLabel1.setText(ImageSetting.RGB.RED.toString());
        sliderLabel2.setText(ImageSetting.RGB.GREEN.toString());
        sliderLabel3.setText(ImageSetting.RGB.BLUE.toString());
        runButton.setEnabled(false);
        cancelButton.setEnabled(false);
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
        });

        // run processing
        runButton.addActionListener(e -> {
            try {
                imageSetting = super.buildImageSetting(lastImage);
                transformSetting = super.buildTransformSetting();
                processingTread = new Thread(() ->
                        callbackProcessing(imageProcessor.processImage(imageSetting, transformSetting)));
                processingTread.start();
                toggleRunCancelButtons();
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        });

        // change RGB/YCrCb schema
        rgbRadioButton.addActionListener(e -> {
            sliderLabel1.setText(ImageSetting.RGB.RED.toString());
            sliderLabel2.setText(ImageSetting.RGB.GREEN.toString());
            sliderLabel3.setText(ImageSetting.RGB.BLUE.toString());
        });
        ycrcbRadioButton.addActionListener(e -> {
            sliderLabel1.setText(ImageSetting.YCRCB.Y.toString());
            sliderLabel2.setText(ImageSetting.YCRCB.CR.toString());
            sliderLabel3.setText(ImageSetting.YCRCB.CB.toString());
        });

        // change sliders event
        slider1.addChangeListener(evt -> {
            sliderValue1.setText(slider1.getValue() + "%");
            updateTotalValue();
        });
        slider2.addChangeListener(evt -> {
            sliderValue2.setText(slider2.getValue() + "%");
            updateTotalValue();
        });
        slider3.addChangeListener(evt -> {
            sliderValue3.setText(slider3.getValue() + "%");
            updateTotalValue();
        });

        // cancel button
        cancelButton.addActionListener(e -> {
            processingTread.interrupt();
            toggleRunCancelButtons();
        });
    }

    private void callbackProcessing(BufferedImage image) {
        imageLabel.setIcon(buildIconForLabel(image, imageLabel));
        resultsFormController.addResult(ResultSetting.startBuilder()
                .withResultImage(image)
                .withImageSetting(imageSetting)
                .withTransformSetting(transformSetting)
                .build());
        if (!resultsFormController.isVisible()) {
            resultsFormController.showUp();
        }
        toggleRunCancelButtons();
    }

    private void updateImage(BufferedImage image) {
        Image dimg = image.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(),
                Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(dimg));
        runButton.setEnabled(true);
    }

    private void toggleRunCancelButtons() {
        cancelButton.setEnabled(!cancelButton.isEnabled());
        runButton.setEnabled(!runButton.isEnabled());
    }

    private void updateTotalValue() {
        totalValue.setText(String.valueOf("Total compression: " +
                (slider1.getValue() + slider2.getValue() + slider3.getValue()) / 3 + "%"));
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
