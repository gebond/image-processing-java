package com.gebond.ip.model.array;

import com.gebond.ip.math.commons.util.ArrayUtil;

/**
 * Created on 10/02/18.
 */
public class Array2D extends ArrayContainer {

  private double[][] array2D;

  private Array2D(){
  }

  public Array2D(double[][] array) {
    setArray(array);
  }

  public static Array2D ofNoCopy(double[][] array) {
    Array2D array2D = new Array2D();
    array2D.array2D = array;
    return array2D;
  }

  public double[][] getArray2DNoCopy(){
    return array2D;
  }

  public static Array2D of(double[][] array2D) {
    return new Array2D(array2D);
  }

  public double[][] getArray2DCopy() {
    return ArrayUtil.copyOf(array2D);
  }

  @Override
  public int getSize() {
    return array2D.length * array2D[0].length;
  }

  @Override
  public Object getArrayCopy() {
    return getArray2DCopy();
  }

  @Override
  protected void setArrayAsCopyOf(Object array) {
    array2D = ArrayUtil.copyOf((double[][]) array);
  }
}
