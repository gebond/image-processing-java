package com.gebond.ip.view;

import javax.swing.*;

import static gebond.ip.domain.manager.LogManager.log;

/**
 * Created on 01/03/18.
 */
public class ResultsForm extends JFrame{

    private static final int WIDTH = 350;
    private static final int HEIGHT = 700;

    private JPanel previewPanel;
    private JPanel contentPanel;
    private JLabel lastProcessedImage;
    private JLabel lastProcessedText;
    private JTree tree1;

    public ResultsForm(){
        super("Results");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setContentPane(previewPanel);
        log("resultsForm initialized");
    }

    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JLabel getLastProcessedImage() {
        return lastProcessedImage;
    }
}
