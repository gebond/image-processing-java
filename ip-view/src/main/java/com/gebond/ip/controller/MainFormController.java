package com.gebond.ip.controller;

import static com.gebond.ip.util.FileChooserUtil.configureJFileChooser;
import static com.gebond.ip.util.ImageIconUtil.updateImageLabel;
import static com.gebond.ip.util.ProcessingUtil.retrieveFuture;

import com.gebond.ip.math.func.image.ImageProcessor;
import com.gebond.ip.math.func.image.ImageProcessorImpl;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.model.setting.TransformSetting;
import com.gebond.ip.view.MainForm;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Created on 28/02/18.
 */
public class MainFormController extends MainForm {

  //util
  private JFileChooser fileChooser;
  // in-memory input image representation
  private BufferedImage lastImage;

  private ResultsFormController resultsFormController;
  private ImageProcessor imageProcessor;
  private ImageSetting imageSetting;
  private TransformSetting transformSetting;

  private CompletableFuture<ResultSetting> resultFuture;

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

  private void adjustComponents() {
    fileChooser = configureJFileChooser();
    for (TransformSetting.TransformationType transformationType : TransformSetting.TransformationType
        .values()) {
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

  private void initListeners() {
    // browse image file
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
      updateImageLabel(lastImage, imageLabel);
      runButton.setEnabled(true);
    });

    // run click button
    runButton.addActionListener(e -> {
      try {
        imageSetting = super.buildImageSetting(lastImage);
        transformSetting = super.buildTransformSetting();
        resultFuture = retrieveFuture(imageProcessor, imageSetting, transformSetting);
        resultFuture.thenAccept(this::updateResultForm);
        toggleRunCancelButtons();
      } catch (RuntimeException ex) {
        JOptionPane.showMessageDialog(this,
            ex.getMessage(),
            "Warning",
            JOptionPane.WARNING_MESSAGE);
      }
    });

    // change RGB/YCrCb color schema
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

    // change slider values event
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

    // cancel click button
    cancelButton.addActionListener(e -> {
      resultFuture.cancel(true);
      toggleRunCancelButtons();
    });
  }

  private void updateResultForm(ResultSetting resultSetting) {
    resultsFormController.addResult(resultSetting);
    toggleRunCancelButtons();
  }

  private void toggleRunCancelButtons() {
    cancelButton.setEnabled(!cancelButton.isEnabled());
    runButton.setEnabled(!runButton.isEnabled());
  }

  private void updateTotalValue() {
    totalValue.setText(String.valueOf("Total compression: " +
        (slider1.getValue() + slider2.getValue() + slider3.getValue()) / 3 + "%"));
  }
}
