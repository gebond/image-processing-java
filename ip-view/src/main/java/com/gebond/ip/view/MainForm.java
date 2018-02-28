package com.gebond.ip.view;

import javax.swing.*;

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
    private JComboBox selectMethodBox;
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

    public JComboBox getSelectMethodBox() {
        return selectMethodBox;
    }

    public JButton getBrowserButton() {
        return browserButton;
    }

    public JLabel getImage() {
        return image;
    }
}
