package com.gebond.ip.util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.apache.commons.io.FilenameUtils;

/**
 * Created on 15/04/18.
 */
public final class FileChooserUtil {

  private static final String PNG = "png";
  private static final String JPG = "jpg";

  private FileChooserUtil() {
  }

  public static JFileChooser configureJFileChooser() {
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
