package com.gebond.ip.math.func.array;

/**
 * Created on 10/02/18.
 */
public abstract class ArrayContainer {

    private int size;

    public int getSize() {
        return size;
    }

    public abstract Object getArray();

    public void setArray(Object array) {
        size = ((double[]) array).length;
        setArrayAsCopyOf(array);
    }

    protected abstract void setArrayAsCopyOf(Object array);
}
