package com.gebond.ip.view;

import javax.swing.*;

/**
 * Created on 01/03/18.
 */
public class ResultsForm extends JFrame{
    private JPanel previewPanel;
    private JPanel contentPanel;
    private JLabel selectedImage;


    public ResultsForm(){
        super("Results");
        setContentPane(previewPanel);
    }


    public JPanel getPreviewPanel() {
        return previewPanel;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    public JLabel getSelectedImage() {
        return selectedImage;
    }
}
