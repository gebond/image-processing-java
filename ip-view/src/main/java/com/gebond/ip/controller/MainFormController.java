package com.gebond.ip.controller;

import com.gebond.ip.view.MainForm;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;

/**
 * Created on 28/02/18.
 */
public class MainFormController {

    private static final String PNG = "png";
    private static final String JPG = "jpg";

    private MainForm mainForm;
    //left panel
    private JButton browserButton;
    private JLabel image;
    //right panel
    //
    //util
    private JFileChooser fileChooser;

    public MainFormController() {
        initComponents();
        initListeners();
    }

    public void show() {
        mainForm.setVisible(true);
    }

    private void initComponents() {
        mainForm = new MainForm();

        browserButton = mainForm.getBrowserButton();
        image = mainForm.getImage();

        fileChooser = configureJFileChooser();
    }

    private void initListeners() {
        // browse image file and st
        browserButton.addActionListener(e -> {
            int result = fileChooser.showDialog(null,
                    "Select");
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    image.setIcon(new ImageIcon(ImageIO.read(file)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
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
        //Add custom icons for file types.
//        fileChooser.setFileView(new ImageFileView());
        //Add the preview pane.
//        fileChooser.setAccessory(new ImagePreview(fc));
        return fileChooser;
    }
}
