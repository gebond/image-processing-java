package com.gebond.ip.math.func.context;

import java.awt.image.BufferedImage;

/**
 * Created on 17/02/18.
 */
public class ImageContext extends OperationContext {

    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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

        public ImageContext build(){
            return imageContext;
        }
    }
}
