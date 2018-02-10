package com.gebond.ip.math.func.operation;

/**
 * Created by Gleb on 21.01.2018.
 */
public abstract class OperationContext {

    private boolean closed = false;

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        this.closed = true;
    }
}
