package com.gebond.ip.model.setting;

/**
 * Created on 21/02/18.
 */
public class TransformSetting {

    private TransformationType type;
    private CompressionSetting compressionSetting;

    public TransformationType getType() {
        return type;
    }

    public void setType(TransformationType type) {
        this.type = type;
    }

    public CompressionSetting getCompressionSetting() {
        return compressionSetting;
    }

    public void setCompressionSetting(CompressionSetting compressionSetting) {
        this.compressionSetting = compressionSetting;
    }

    public enum TransformationType {
        HAART_TRANSFORM,
        WALSH_TRANSFORM,
        DESCRETE_TRANSFORM,
    }


    public static TransformSettingBuilder startBuilder() {
        return new TransformSettingBuilder();
    }

    public static class TransformSettingBuilder {
        private TransformSetting transformSetting;

        public TransformSettingBuilder() {
            transformSetting = new TransformSetting();
        }

        public TransformSettingBuilder withType(TransformationType type) {
            this.transformSetting.type = type;
            return this;
        }

        public TransformSettingBuilder withCompression(CompressionSetting compression) {
            this.transformSetting.compressionSetting = compression;
            return this;
        }

        public TransformSetting build() {
            return transformSetting;
        }
    }
}
