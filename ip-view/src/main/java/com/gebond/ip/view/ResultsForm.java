package com.gebond.ip.view;

import static gebond.ip.domain.manager.LogManager.log;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Created on 01/03/18.
 */
public class ResultsForm extends JFrame {

  private static final int WIDTH = 350;
  private static final int HEIGHT = 700;

  protected JPanel containerPanel;
  protected JPanel previewPanel;
  protected JPanel contentPanel;
  protected JLabel lastProcessedImage;
  protected JLabel lastProcessedText;
  protected JPanel resultContent;
  protected JButton clearResultsButton;

  public ResultsForm() {
    super("Results");
    setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    setSize(WIDTH, HEIGHT);
    setContentPane(containerPanel);
    resultContent.setMaximumSize(new Dimension(3, Integer.MAX_VALUE));
    log("resultsForm initialized");
  }
}
