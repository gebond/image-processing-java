package com.gebond.ip.model.array;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created on 07/05/18.
 */
class ListVectorTest {

  @Test
  @DisplayName("ListVector default")
  void ListVector_default() {
    ListVector<Object> testee = new ListVector<>(new Integer[]{});

    assertEquals(0, testee.getSize());
  }


  @Test
  @DisplayName("ListVector copyOfArray")
  void ListVector_asArray() {
    ListVector<Integer> testee = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    assertEquals(5, testee.getSize());
    assertEquals(Integer.valueOf(1), testee.get(0));
    assertEquals(Integer.valueOf(2), testee.get(1));
    assertEquals(Integer.valueOf(3), testee.get(2));
    assertEquals(Integer.valueOf(4), testee.get(3));
    assertEquals(Integer.valueOf(5), testee.get(4));
  }


  @Test
  @DisplayName("ListVector copyOfListVector")
  void ListVector_asListVector() {
    ListVector<Integer> target = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    ListVector<Integer> testee = new ListVector<>(target);

    assertEquals(5, testee.getSize());
    assertEquals(Integer.valueOf(1), testee.get(0));
    assertEquals(Integer.valueOf(2), testee.get(1));
    assertEquals(Integer.valueOf(3), testee.get(2));
    assertEquals(Integer.valueOf(4), testee.get(3));
    assertEquals(Integer.valueOf(5), testee.get(4));
  }

  @Test
  @DisplayName("ListVector copyOfListVector without changes")
  void ListVector_doNotAcceptChanges() {
    ListVector<Integer> target = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    ListVector<Integer> testee = new ListVector<>(target);
    target.set(1, Integer.valueOf(101));

    assertEquals(5, testee.getSize());
    assertEquals(Integer.valueOf(1), testee.get(0));
    assertEquals(Integer.valueOf(2), testee.get(1));
    assertEquals(Integer.valueOf(3), testee.get(2));
    assertEquals(Integer.valueOf(4), testee.get(3));
    assertEquals(Integer.valueOf(5), testee.get(4));
  }

  @Test
  @DisplayName("ListVector set value")
  void ListVector_setValue() {
    ListVector<Integer> testee = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    testee.set(0, Integer.valueOf(10));
    testee.set(4, Integer.valueOf(101));

    assertEquals(Integer.valueOf(10), testee.get(0));
    assertEquals(Integer.valueOf(101), testee.get(4));
  }

  @Test
  @DisplayName("ListVector copyOfGeneralArray")
  void ListVector_asGeneralArray() {
    ListVector<Integer> testee = ListVector
        .of(Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));

    assertEquals(3, testee.getSize());
    assertEquals(Integer.valueOf(1), testee.get(0));
    assertEquals(Integer.valueOf(2), testee.get(1));
    assertEquals(Integer.valueOf(3), testee.get(2));
  }

  @Test
  @DisplayName("ListVector true equals")
  void equals_true() {
    ListVector<Integer> target = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});
    ListVector<Integer> testee = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    assertEquals(testee, testee);
    assertEquals(target, testee);
  }

  @Test
  @DisplayName("ListVector false equals")
  void equals_false() {
    ListVector<Object> target = new ListVector<>(new Integer[]{1, 2, 3, 4, 5});

    ListVector<Object> testee = new ListVector<>(new Integer[]{1, 2, 3, 4, 6});
    assertNotEquals(target, testee);

    testee = new ListVector<>(new Integer[]{1, 2, 3});
    assertNotEquals(target, testee);

    testee = new ListVector<Object>(new Double[]{1.0, 2.0, 3.0});
    assertNotEquals(target, testee);

    assertNotEquals(target, null);
  }

  @Test
  @DisplayName("ListVector toArray")
  void toArray_accept() {
    Integer[] sourceArray = new Integer[]{1, 2, 3, 4, 5};
    ListVector<Integer> testee = new ListVector<Integer>(sourceArray);

    assertArrayEquals(sourceArray, testee.toArray());
  }

  @Test
  @DisplayName("ListVector size")
  void getSize_accept() {
    Integer[] sourceArray = new Integer[]{1, 2, 3, 4, 5};
    ListVector<Integer> testee = new ListVector<Integer>(sourceArray);

    assertEquals(5, testee.getSize());
  }

  @Test
  @DisplayName("ListVector from base vector")
  void vector_acceptBaseVector() {
    Vector<Integer> base = new ListVector<>(1, 2, 3, 4, 5);

    ListVector<Integer> testee = new ListVector<>(base);
    assertEquals(base, testee);

    base.set(2, 6);
    assertNotEquals(base, testee);
  }

  @Test
  @DisplayName("ListVector multiplied")
  void multiply_apply() {
    Vector<Integer> base = new ListVector<>(1, 2, 3);
    Vector<Integer> target = new ListVector<>(2, 4, 6);

    assertEquals(28, base.multiply(target));
    assertEquals(28, target.multiply(base));
  }
}