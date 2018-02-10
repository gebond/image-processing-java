package com.gebond.ip.math.func.operation;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Created by Gleb on 21.01.2018.
 */
public abstract class OperationManager<T extends OperationContext> {
    abstract List<Operation<T>> getOperations();

    public T process(T context) {
        for (Operation<T> operation : getOperations()) {
            if (context.isClosed()) {
                return context;
            }
            try {
                operation.validate(context);
            } catch (ValidationException e) {
                e.printStackTrace();
                context.close();
                break;
            }
            operation.apply(context);
        }
        return context;
    }
}
