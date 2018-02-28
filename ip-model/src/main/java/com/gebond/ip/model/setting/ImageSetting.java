package com.gebond.ip.model.setting;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 17/02/18.
 */
public class ImageSetting {

    private ImageSchema imageSchema;
    private Map<RGB, Double> imageValues = new HashMap<>();
    private SegmentSize segmentSize;


    public ImageSchema getImageSchema() {
        return imageSchema;
    }

    public void setImageSchema(ImageSchema imageSchema) {
        this.imageSchema = imageSchema;
    }

    public Map<RGB, Double> getImageValues() {
        return imageValues;
    }

    public void setImageValues(Map<RGB, Double> imageValues) {
        this.imageValues = imageValues;
    }

    public SegmentSize getSegmentSize() {
        return segmentSize;
    }

    public void setSegmentSize(SegmentSize segmentSize) {
        this.segmentSize = segmentSize;
    }

    public enum SegmentSize {
        X4() {
            @Override
            public int getValue() {
                return 4;
            }
        },
        X8() {
            @Override
            public int getValue() {
                return 8;
            }
        },
        X16() {
            @Override
            public int getValue() {
                return 16;
            }
        },
        X32() {
            @Override
            public int getValue() {
                return 32;
            }
        };

        private int value;

        public int getValue() {
            return value;
        }

        public static SegmentSize valueOf(int value) {
            for (SegmentSize segmentSize : SegmentSize.values()) {
                if (segmentSize.value == value) {
                    return segmentSize;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public enum ImageSchema {
        RGB,
        YCRCB
    }

    public enum RGB {
        RED,
        GREEN,
        BLUE
    }

    public enum YCRCB {
        Y,
        CR,
        CB
    }

    public static ImageSettingBuilder startBuilder() {
        return new ImageSettingBuilder();
    }

    public static class ImageSettingBuilder {
        private ImageSetting imageSetting;

        public ImageSettingBuilder() {
            imageSetting = new ImageSetting();
        }

        public ImageSettingBuilder withRGB(double red, double green, double blue) {
            imageSetting.imageSchema = ImageSchema.RGB;
            imageSetting.imageValues.put(RGB.RED, red);
            imageSetting.imageValues.put(RGB.GREEN, green);
            imageSetting.imageValues.put(RGB.BLUE, blue);
            return this;
        }

        public ImageSettingBuilder withSegmentSize(SegmentSize segmentSize) {
            imageSetting.segmentSize = segmentSize;
            return this;
        }

        public ImageSettingBuilder withYCrCb(double y, double cr, double cb) {
            imageSetting.imageSchema = ImageSchema.YCRCB;
            return this;
        }

        public ImageSetting build() {
            return imageSetting;
        }
    }
}
