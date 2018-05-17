package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.func.transform.FourierTransform.DiscreteFourierTransform.doAnalysis;
import static com.gebond.ip.math.func.transform.FourierTransform.DiscreteFourierTransform.doSynthesis;
import static com.gebond.ip.math.func.util.Functions.discreteLength;

import com.gebond.ip.math.func.compression.CompressionOperation2D;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.context.FourierContext.FourierContext2D;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;
import com.gebond.ip.model.array.Array2D;
import com.gebond.ip.model.array.Vector3D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gleb on 16.04.2018.
 */
public class DiscreteTransformation2D extends OperationManager<FourierContext.FourierContext2D> {

  @Override
  public List<Operation<FourierContext2D>> getOperations() {
    return Collections.emptyList();
  }

  @Override
  public List<Operation<FourierContext.FourierContext2D>> getOperations(
      FourierContext.FourierContext2D context2D) {
    return Arrays.asList(
        new DiscreteAnalysis2D(context2D),
        new CompressionOperation2D(),
        new DiscreteSynthesis2D(context2D));
  }

  public static class DiscreteAnalysis2D implements Operation<FourierContext2D> {

    private final Vector3D<Integer> params;

    DiscreteAnalysis2D(FourierContext.FourierContext2D context) {
      params = new Vector3D<>(context.getDiscreteSetting().getP(),
          context.getDiscreteSetting().getS(),
          context.getDiscreteSetting().getN());
    }

    @Override
    public boolean validate(FourierContext2D context) throws IllegalArgumentException {
      if (context.getDiscreteSetting().getSize() !=
          discreteLength(context.getDiscreteSetting().getP(),
              context.getDiscreteSetting().getS(),
              context.getDiscreteSetting().getN())) {
        throw new IllegalArgumentException("Wrong configuration {p, s, N, len}");
      }
      return true;
    }

    @Override
    public void apply(FourierContext.FourierContext2D context) {
      int size = context.getDiscreteSetting().getSize();
      Array2D array2D = context.getFourierData();
      for (int i = 0; i < size; i++) {
        array2D.setRow(i, doAnalysis(params, array2D.getRow(i)));
      }
    }
  }

  public static class DiscreteSynthesis2D implements Operation<FourierContext2D> {

    private final Vector3D<Integer> params;

    DiscreteSynthesis2D(FourierContext.FourierContext2D context) {
      params = new Vector3D<>(context.getDiscreteSetting().getP(),
          context.getDiscreteSetting().getS(),
          context.getDiscreteSetting().getN());
    }

    @Override
    public boolean validate(FourierContext2D context) throws IllegalArgumentException {
      if (context.getDiscreteSetting().getSize() !=
          discreteLength(context.getDiscreteSetting().getP(),
              context.getDiscreteSetting().getS(),
              context.getDiscreteSetting().getN())) {
        throw new IllegalArgumentException("Wrong configuration {p, s, N, len}");
      }
      return true;
    }

    @Override
    public void apply(FourierContext.FourierContext2D context) {
      int size = context.getDiscreteSetting().getSize();
      Array2D array2D = context.getFourierData();
      for (int i = 0; i < size; i++) {
        array2D.setRow(i, doSynthesis(params, array2D.getRow(i)));
      }
    }
  }
}
