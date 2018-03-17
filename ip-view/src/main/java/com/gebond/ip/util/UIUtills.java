package com.gebond.ip.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created on 05/03/18.
 */
public class UIUtills {

    public static Color SMOOTH_GREEN = new Color(113, 169, 106);
    public static Color SMOOTH_RED = new Color(117, 114, 92);

    public static ImageIcon buildIconForLabel(BufferedImage bufferedImage, JLabel label) {
        return buildIconForDimension(bufferedImage, label.getWidth(), label.getHeight());
    }

    public static ImageIcon buildIconForDimension(BufferedImage bufferedImage, int width, int height) {
        return new ImageIcon(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
