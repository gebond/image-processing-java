package com.gebond.ip.math.func.array;

/**
 * Created on 10/02/18.
 */
public abstract class ArrayContainer {

    public abstract int getSize();
    public abstract Object getArrayCopy();
    protected abstract void setArrayAsCopyOf(Object array);

    public void setArray(Object array) {
        setArrayAsCopyOf(array);
    }
}
