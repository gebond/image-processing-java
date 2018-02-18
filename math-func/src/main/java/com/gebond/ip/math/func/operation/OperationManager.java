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
            try {
                if (operation.validate(context)) {
                    operation.apply(context);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                context.close();
                break;
            }
        }
        return context;
    }
}
