package com.gebond.ip.math.func.operation;

import javax.xml.bind.ValidationException;

/**
 * Created by Gleb on 21.01.2018.
 */
public interface Operation<T extends OperationContext> {
    void validate(T context) throws ValidationException;
    void apply(T context);
}
