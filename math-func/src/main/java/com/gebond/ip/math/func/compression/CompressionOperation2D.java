package com.gebond.ip.math.func.compression;

import static com.gebond.ip.model.setting.CompressionSetting.MIN_COMPRESSION_RATE;
import static org.apache.commons.math3.util.FastMath.abs;

import com.gebond.ip.math.func.context.FourierContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 10/02/18.
 */
public class CompressionOperation2D extends CompressingOperation<FourierContext.FourierContext2D> {

  @Override
  public void apply(FourierContext.FourierContext2D context) {
    if (context.getCompressionSetting().getCompressionRate() == MIN_COMPRESSION_RATE) {
      return; // no compression is required
    }
    double[][] target = context.getFourierData().getArray2DNoCopy();
    List<Value> values = new ArrayList<>();
    for (int i = 0; i < target.length; i++) {
      for (int j = 0; j < target.length; j++) {
        values.add(new Value(i, j, target[i][j]));
      }
    }

    values = sortAndSetZero(values, target.length * target[0].length, context);

    for (Value value : values) {
      target[value.i][value.j] = value.value;
    }
    context.getFourierData().setArray(target);
  }

  private class Value implements ValueGetterSetter {

    final int i;
    final int j;
    double value;

    public Value(int i, int j, double value) {
      this.i = i;
      this.j = j;
      this.value = value;
    }

    @Override
    public void setValue(double val) {
      value = val;
    }

    @Override
    public int compareTo(Object o) {
      Value valObj = (Value) o;
      if (abs(value) < abs(valObj.value)) {
        return -1;
      } else if (abs(value) > abs(valObj.value)) {
        return 1;
      }
      return 0;
    }
  }
}
