package com.gebond.ip.model.array;

/**
 * Created on 07/05/18.
 */
public interface Vector<T> {

  int getSize();

  T get(int i);

  void set(int i,  T value);

  T[] toArray();

  double multiply(Vector<T> anotherVector);
}
