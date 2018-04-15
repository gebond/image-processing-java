package com.gebond.ip.math.func.operation;

import com.gebond.ip.math.func.context.OperationContext;

/**
 * Created by Gleb on 21.01.2018.
 */
public interface Operation<T extends OperationContext> {

  boolean validate(T context) throws IllegalArgumentException;

  void apply(T context);

  default String description() {
    return "Default Operation";
  }
}
