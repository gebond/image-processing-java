package com.gebond.ip.model.array;

/**
 * Created on 17/02/18.
 */
public class Vector3D<T> implements Vector<T> {

  private ListVector<T> vector;

  public Vector3D(T x, T y, T z) {
    vector = new ListVector<>(x, y, z);
  }

  public T getX() {
    return vector.get(0);
  }

  public void setX(T x) {
    vector.set(0, x);
  }

  public T getY() {
    return vector.get(1);
  }

  public void setY(T y) {
    vector.set(1, y);
  }

  public T getZ() {
    return vector.get(2);
  }

  public void setZ(T z) {
    vector.set(2, z);
  }

  @Override
  public int getSize() {
    return 3;
  }

  @Override
  public T get(int i) {
    if (i > getSize()) {
      throw new IllegalArgumentException("Size is 3!");
    }
    return vector.get(i);
  }

  @Override
  public void set(int i, T value) {
    if (i >= getSize()) {
      throw new IllegalArgumentException("Size is 3!");
    }
    vector.set(i, value);
  }

  @Override
  public T[] toArray() {
    return vector.toArray();
  }

  @Override
  public double multiply(Vector<T> anotherVector) {
    return 0.0;
  }
}
