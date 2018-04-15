package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.context.OperationContext;
import java.util.List;

/**
 * Created by Gleb on 21.01.2018.
 */
public abstract class OperationManager<T extends OperationContext> {

  public abstract List<Operation<T>> getOperations();

  public T process(T context) {
    for (Operation<T> operation : getOperations()) {
      if (context.isClosed()) {
        return context;
      }
      if (operation.validate(context)) {
//                System.out.println(operation.description());
        operation.apply(context);
      }
    }
    return context;
  }
}
