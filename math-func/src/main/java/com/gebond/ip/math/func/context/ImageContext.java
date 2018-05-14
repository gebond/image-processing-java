package com.gebond.ip.math.func.context;

import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.Vector3D;
import com.gebond.ip.model.setting.ImageSetting;
import com.gebond.ip.model.setting.ResultSetting;
import com.gebond.ip.model.setting.TransformSetting;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17/02/18.
 */
public class ImageContext extends OperationContext {

  private final ResultSetting resultSetting;
  private final Long startTime;
  private BufferedImage image;
  private int columnCount;
  private int rowCount;
  /**
   * List of vectors where vector contains (x-red, y-green, z-blue, ...)
   */
  private List<Vector3D<Array2D>> pixelList;
  /**
   * Determines whether single threaded operations will be used or not
   */
  public final boolean IS_SINGLE_THREAD = false;

  public ImageContext() {
    startTime = System.nanoTime();
    pixelList = new ArrayList<>();
    resultSetting = new ResultSetting();
    rowCount = 0;
    columnCount = 0;
  }

  public static ImageContextBuilder startBuilder() {
    return new ImageContextBuilder();
  }

  public Long getStartTime() {
    return startTime;
  }

  public BufferedImage getImage() {
    return image;
  }

  public void setImage(BufferedImage image) {
    this.image = image;
  }

  public int getColumnCount() {
    return columnCount;
  }

  public void setColumnCount(int columnCount) {
    this.columnCount = columnCount;
  }

  public int getRowCount() {
    return rowCount;
  }

  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }

  public List<Vector3D<Array2D>> getPixelList() {
    return pixelList;
  }

  public void setPixelList(List<Vector3D<Array2D>> pixelList) {
    this.pixelList = pixelList;
  }

  public ResultSetting getResultSetting() {
    return resultSetting;
  }

  public static class ImageContextBuilder {

    private ImageContext imageContext;

    public ImageContextBuilder() {
      this.imageContext = new ImageContext();
    }

    public ImageContextBuilder withSetting(ImageSetting imageSetting) {
      imageContext.getResultSetting().setImageSetting(imageSetting);
      return this;
    }

    public ImageContextBuilder withSetting(TransformSetting transformSetting) {
      imageContext.getResultSetting().setTransformSetting(transformSetting);
      return this;
    }

    public ImageContext build() {
      return imageContext;
    }
  }
}
