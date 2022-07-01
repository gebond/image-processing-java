package com.gebond.ip.model.array;

import static com.gebond.ip.math.commons.util.ArrayUtil.copyOf;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created on 07/05/18.
 */
public class ListVector<T> implements Vector<T> {

  private List<T> values;

  public ListVector(T... array1D) {
    this.values = Arrays.asList(array1D);
  }

  public ListVector(Vector<T> vector) {
    this.values = Arrays.asList((T[]) copyOf(vector.toArray()));
  }

  public static <T> ListVector<T> of(T... array) {
    return new ListVector<>(array);
  }

  @Override
  public T get(int i) {
    return this.values.get(i);
  }

  @Override
  public void set(int i, T value) {
    this.values.set(i, value);
  }

  @Override
  public int getSize() {
    return values.size();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListVector<T> ListVector = (ListVector<T>) o;
    return ListVector.getSize() == ListVector.getSize() &&
        Objects.equals(values, ListVector.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getSize(), values);
  }

  @Override
  public T[] toArray() {
    return (T[]) values.toArray();
  }

  @Override
  //TODO !!@#!#!@#!@#
  public double multiply(Vector<T> anotherVector) {
    double result = 0.0;
    for (int i = 0; i < anotherVector.getSize(); i++) {
      result += (Integer) this.get(i) * ((Integer) anotherVector.get(i));
    }
    return result;
  }
}
