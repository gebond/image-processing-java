package com.gebond.ip.model.setting;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 17/02/18.
 */
public class ImageSetting {

  // Example: { 85, 15, 35}
  private final Map<Integer, CompressionSetting> imageValues = new HashMap<>();
  private BufferedImage sourceImage;
  private ImageSchema imageSchema;
  private SegmentSize segmentSize;

  public static ImageSettingBuilder startBuilder() {
    return new ImageSettingBuilder();
  }

  public ImageSchema getImageSchema() {
    return imageSchema;
  }

  public void setImageSchema(ImageSchema imageSchema) {
    this.imageSchema = imageSchema;
  }

  public Map<Integer, CompressionSetting> getImageValues() {
    return imageValues;
  }

  public SegmentSize getSegmentSize() {
    return segmentSize;
  }

  public void setSegmentSize(SegmentSize segmentSize) {
    this.segmentSize = segmentSize;
  }

  public BufferedImage getSourceImage() {
    return sourceImage;
  }

  public void setSourceImage(BufferedImage sourceImage) {
    this.sourceImage = sourceImage;
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

    public static SegmentSize valueOf(int value) {
      for (SegmentSize segmentSize : SegmentSize.values()) {
        if (segmentSize.value == value) {
          return segmentSize;
        }
      }
      throw new IllegalArgumentException();
    }

    public int getValue() {
      return value;
    }
  }

  public enum ImageSchema {
    RGB {
      @Override
      public int getAmount() {
        return 3;
      }
    },
    YCRCB {
      @Override
      public int getAmount() {
        return 3;
      }
    };

    int amount;

    public int getAmount() {
      return amount;
    }
  }

  public enum RGB {
    RED {
      @Override
      public String toString() {
        return "Red";
      }

      @Override
      public int getOrder() {
        return 0;
      }
    },
    GREEN {
      @Override
      public String toString() {
        return "Green";
      }

      @Override
      public int getOrder() {
        return 1;
      }
    },
    BLUE {
      @Override
      public String toString() {
        return "Blue";
      }

      @Override
      public int getOrder() {
        return 2;
      }
    };

    int order;

    public int getOrder() {
      return order;
    }
  }

  public enum YCRCB {
    Y {
      @Override
      public String toString() {
        return "Y";
      }

      @Override
      public int getOrder() {
        return 0;
      }
    },
    CR {
      @Override
      public String toString() {
        return "Cr";
      }

      @Override
      public int getOrder() {
        return 1;
      }
    },
    CB {
      @Override
      public String toString() {
        return "Cb";
      }

      @Override
      public int getOrder() {
        return 2;
      }
    };

    int order;

    public int getOrder() {
      return order;
    }
  }

  public static class ImageSettingBuilder {

    private ImageSetting imageSetting;

    public ImageSettingBuilder() {
      imageSetting = new ImageSetting();
    }

    public ImageSettingBuilder withSchema(ImageSchema imageSchema) {
      imageSetting.imageSchema = imageSchema;
      return this;
    }

    public ImageSettingBuilder withCompressions(Map<Integer, CompressionSetting> compressions) {
      imageSetting.imageValues.putAll(compressions);
      return this;
    }

    public ImageSettingBuilder withImage(BufferedImage bufferedImage) {
      imageSetting.sourceImage = bufferedImage;
      return this;
    }

    public ImageSettingBuilder withSegmentSize(SegmentSize segmentSize) {
      imageSetting.segmentSize = segmentSize;
      return this;
    }

    public ImageSetting build() {
      return imageSetting;
    }
  }
}
