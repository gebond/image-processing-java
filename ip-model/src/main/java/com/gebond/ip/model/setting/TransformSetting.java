package com.gebond.ip.model.setting;

import com.gebond.ip.model.metric.Metrics;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 21/02/18.
 */
public class TransformSetting {

  private final Set<Metrics.MetricsType> metricsTypes;
  private TransformationType type;
  @Deprecated
  private CompressionSetting compressionSetting;
  private DiscreteSetting discreteSetting;

  public TransformSetting() {
    metricsTypes = new HashSet<>();
  }

  public static TransformSettingBuilder startBuilder() {
    return new TransformSettingBuilder();
  }

  public TransformationType getType() {
    return type;
  }

  public void setType(TransformationType type) {
    this.type = type;
  }

  public Set<Metrics.MetricsType> getMetricsTypes() {
    return metricsTypes;
  }

  @Deprecated
  public CompressionSetting getCompressionSetting() {
    return compressionSetting;
  }

  public DiscreteSetting getDiscreteSetting() {
    return discreteSetting;
  }

  public void setDiscreteSetting(DiscreteSetting discreteSetting) {
    this.discreteSetting = discreteSetting;
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

  public static class TransformSettingBuilder {

    private TransformSetting transformSetting;

    public TransformSettingBuilder() {
      transformSetting = new TransformSetting();
    }

    public TransformSettingBuilder withType(TransformationType type) {
      this.transformSetting.type = type;
      return this;
    }

    public TransformSettingBuilder addMetrics(Metrics.MetricsType metricsType) {
      this.transformSetting.metricsTypes.add(metricsType);
      return this;
    }

    @Deprecated
    public TransformSettingBuilder withCompression(CompressionSetting compression) {
      this.transformSetting.compressionSetting = compression;
      return this;
    }

    public TransformSettingBuilder withDiscreteSettings(DiscreteSetting discreteSetting) {
      this.transformSetting.discreteSetting = discreteSetting;
      return this;
    }

    public TransformSetting build() {
      return transformSetting;
    }
  }
}
