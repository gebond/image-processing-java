package com.gebond.ip.math.func.util;

import com.gebond.ip.math.func.context.FourierContext.FourierContext2D;
import com.gebond.ip.math.func.operation.OperationManager;
import com.gebond.ip.math.func.transform.HaartTransformation2D;
import com.gebond.ip.math.func.transform.WalshTransformation2D;
import com.gebond.ip.model.setting.TransformSetting.TransformationType;

/**
 * Created on 16/04/18.
 */
public class ProcessingUtil {

  private ProcessingUtil() {
  }

  public static OperationManager<FourierContext2D> buildTransformForType(
      TransformationType transformationType) {
    OperationManager<FourierContext2D> transformation2D;
    switch (transformationType) {
      case HAART_TRANSFORM:
        transformation2D = new HaartTransformation2D();
        break;
      case WALSH_TRANSFORM:
        transformation2D = new WalshTransformation2D();
        break;
      default:
        transformation2D = new HaartTransformation2D();
    }
    return transformation2D;
  }
}
