package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.context.OperationContext;
import java.util.List;

/**
 * Created by Gleb on 21.01.2018.
 */
public abstract class OperationManager<T extends OperationContext> {

  public abstract List<Operation<T>> getOperations();

  protected List<Operation<T>> getOperations(T context){
    return getOperations();
  }

  public T process(T context) {
    for (Operation<T> operation : getOperations(context)) {
      if (context.isClosed()) {
        return context;
      }
      if (operation.validate(context)) {
        operation.apply(context);
      }
    }
    return context;
  }
}
