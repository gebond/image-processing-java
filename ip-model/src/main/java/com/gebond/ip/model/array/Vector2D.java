package com.gebond.ip.model.array;

/**
 * Created on 17/02/18.
 */
public class Vector2D<T> implements Vector<T> {

  private ListVector<T> vector;

  public Vector2D(T x, T y) {
    vector = new ListVector<>(x, y);
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

  @Override
  public int getSize() {
    return 2;
  }

  @Override
  public T get(int i) {
    if (i > getSize()) {
      throw new IllegalArgumentException("Size is 2!");
    }
    return vector.get(i);
  }

  @Override
  public void set(int i, T value) {
    if (i >= getSize()) {
      throw new IllegalArgumentException("Size is 2!");
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
