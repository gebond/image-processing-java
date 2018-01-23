package com.gebond.ip.math.func.operation;

/**
 * Created by Gleb on 21.01.2018.
 */
public interface Operation<T extends OperationContext> {
    boolean isValid(T context);
    void apply(T context);
}
