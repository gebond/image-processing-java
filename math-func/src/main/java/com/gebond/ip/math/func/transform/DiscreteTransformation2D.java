package com.gebond.ip.math.func.transform;

import static com.gebond.ip.math.func.util.Functions.discreteLength;

import com.gebond.ip.math.func.compression.CompressionOperation2D;
import com.gebond.ip.math.func.context.FourierContext;
import com.gebond.ip.math.func.context.FourierContext.FourierContext2D;
import com.gebond.ip.math.func.operation.Operation;
import com.gebond.ip.math.func.operation.OperationManager;
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

    private final int p;
    private final int s;

    DiscreteAnalysis2D(FourierContext.FourierContext2D context) {
      this.p = context.getDiscreteSetting().getP();
      this.s = context.getDiscreteSetting().getS();
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
      context.getFourierData();
    }
  }

  public static class DiscreteSynthesis2D implements Operation<FourierContext2D> {

    private final int p;
    private final int s;

    DiscreteSynthesis2D(FourierContext.FourierContext2D context) {
      this.p = context.getDiscreteSetting().getP();
      this.s = context.getDiscreteSetting().getS();
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

    }
  }
}
