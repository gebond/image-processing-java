package com.gebond.ip.math.func.context;

import com.gebond.ip.math.func.array.Array2D;
import com.gebond.ip.math.func.array.Vector;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17/02/18.
 */
public class ImageContext extends OperationContext {

    private BufferedImage image;
    private ImageSetting imageSetting;
    private int columnCount = 0;
    private int rowCount = 0;
    private List<Vector<Array2D>> pixelList = new ArrayList<>();

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public ImageSetting getImageSetting() {
        return imageSetting;
    }

    public void setImageSetting(ImageSetting imageSetting) {
        this.imageSetting = imageSetting;
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

    public List<Vector<Array2D>> getPixelList() {
        return pixelList;
    }

    public void setPixelList(List<Vector<Array2D>> pixelList) {
        this.pixelList = pixelList;
    }

    public static ImageContextBuilder startBuilder() {
        return new ImageContextBuilder();
    }

    public static class ImageContextBuilder {
        private ImageContext imageContext;

        public ImageContextBuilder() {
            this.imageContext = new ImageContext();
        }

        public ImageContextBuilder withImage(BufferedImage image) {
            imageContext.setImage(image);
//            imageContext.setImage(ImageUtil.toBufferedImage(image));
            return this;
        }

        public ImageContextBuilder withSetting(ImageSetting imageSetting) {
            imageContext.setImageSetting(imageSetting);
            return this;
        }

        public ImageContext build() {
            return imageContext;
        }
    }
}
