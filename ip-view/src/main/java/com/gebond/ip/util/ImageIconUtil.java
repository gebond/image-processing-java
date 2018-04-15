package com.gebond.ip.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Created on 05/03/18.
 */
public class ImageIconUtil {

  private ImageIconUtil() {
  }

  public static ImageIcon buildIconForLabel(BufferedImage image, JLabel targetLabel) {
    return buildIconForDimension(image, targetLabel.getWidth(), targetLabel.getHeight());
  }

  public static ImageIcon buildIconForDimension(BufferedImage image, int width, int height) {
    return new ImageIcon(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
  }

  public static void updateImageLabel(BufferedImage image, JLabel targetLabel) {
    Image dimg = image.getScaledInstance(targetLabel.getWidth(), targetLabel.getHeight(),
        Image.SCALE_SMOOTH);
    targetLabel.setIcon(new ImageIcon(dimg));
  }
}
