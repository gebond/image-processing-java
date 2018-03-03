package com.gebond.ip.model.setting;

/**
 * Created on 21/02/18.
 */
public class TransformSetting {

    private TransformationType type;
    @Deprecated
    private CompressionSetting compressionSetting;

    public TransformationType getType() {
        return type;
    }

    public void setType(TransformationType type) {
        this.type = type;
    }

    @Deprecated
    public CompressionSetting getCompressionSetting() {
        return compressionSetting;
    }

    public enum TransformationType {
        HAART_TRANSFORM {
            @Override
            public String toString() {
                return "Haart transformation";
            }
        },
        WALSH_TRANSFORM {
            @Override
            public String toString() {
                return "Walsh transformation";
            }
        },
        DISCRETE_TRANSFORM {
            @Override
            public String toString() {
                return "Discrete transformation";
            }
        }
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

        @Deprecated
        public TransformSettingBuilder withCompression(CompressionSetting compression) {
            this.transformSetting.compressionSetting = compression;
            return this;
        }

        public TransformSetting build() {
            return transformSetting;
        }
    }
}
