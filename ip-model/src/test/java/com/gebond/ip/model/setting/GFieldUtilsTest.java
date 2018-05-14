package com.gebond.ip.model.setting;

import static org.apache.commons.math3.util.FastMath.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gebond.ip.model.array.ListVector;
import com.gebond.ip.model.array.Vector;
import com.gebond.ip.model.array.Vector2D;
import com.gebond.ip.model.array.Vector3D;
import com.gebond.ip.model.setting.GFieldCacheUtils.GField;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created on 07/05/18.
 */
@DisplayName("Cached gfield tests")
class GFieldCacheUtilsTest {


  @Test
  @DisplayName("Cached Gfield(p=2, s=2)")
  void get_cached() {
    Vector2D<Integer> params = new Vector2D<>(2, 2);

    GField result = GFieldCacheUtils.get(params);

    assertNotNull(result);
    assertEquals(4, result.alphas.size());
  }

  @Test
  @DisplayName("Cached Gfield(p=2, s=3)")
  void get_cached_2() {
    Vector2D<Integer> params = new Vector2D<>(2, 3);

    GField result = GFieldCacheUtils.get(params);

    assertNotNull(result);
    assertEquals(8, result.alphas.size());
  }

  @Test
  @DisplayName("Cached Gfield(p=3, s=2)")
  void get_cached_3() {
    Vector2D<Integer> params = new Vector2D<>(3, 2);

    GField result = GFieldCacheUtils.get(params);

    assertNotNull(result);
    assertEquals(9, result.alphas.size());
  }

  @Test
  @DisplayName("Converts to Number p=2 s=2 n=2  max=16")
  void convertToInt_acceptCorrect() {
    // N = 2
    int p = 2;
    int s = 2;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0),
        new ListVector<Integer>(0, 0))));
    assertEquals(6, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 1),
        new ListVector<Integer>(1, 0))));
    assertEquals(15, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(1, 1),
        new ListVector<Integer>(1, 1))));
  }

  @Test
  @DisplayName("Converts to Number p=2 s=3 n=2  max=64")
  void convertToInt_acceptCorrect2() {
    // N = 2
    int p = 2;
    int s = 3;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0, 0),
        new ListVector<Integer>(0, 0, 0))));
    assertEquals(42, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 1, 0),
        new ListVector<Integer>(1, 0, 1))));
    assertEquals(63, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(1, 1, 1),
        new ListVector<Integer>(1, 1, 1))));
  }

  @Test
  @DisplayName("Converts to Number p=2 s=4 n=2  max=256")
  void convertToInt_acceptCorrect5() {
    // N = 2
    int p = 2;
    int s = 4;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0, 0, 0),
        new ListVector<Integer>(0, 0, 0, 0))));
    assertEquals(90, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 1, 0, 1),
        new ListVector<Integer>(1, 0, 1, 0))));
    assertEquals(255, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(1, 1, 1, 1),
        new ListVector<Integer>(1, 1, 1, 1))));
  }

  @Test
  @DisplayName("Converts to Number p=3 s=2 n=2 max=81")
  void convertToInt_acceptCorrect3() {
    // N = 2
    int p = 3;
    int s = 2;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0),
        new ListVector<Integer>(0, 0))));
    assertEquals(21, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 1),
        new ListVector<Integer>(2, 0))));
    assertEquals(80, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(2, 2),
        new ListVector<Integer>(2, 2))));
  }

  @Test
  @DisplayName("Converts to Number p=3 s=3  n=2 max=729")
  void convertToInt_acceptCorrect4() {
    // N = 2
    int p = 3;
    int s = 3;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0, 0),
        new ListVector<Integer>(0, 0, 0))));
    assertEquals(519, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 2, 0),
        new ListVector<Integer>(1, 0, 2))));
    assertEquals(728, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(2, 2, 2),
        new ListVector<Integer>(2, 2, 2))));
  }

  @Test
  @DisplayName("Converts to Number p=5 s=2 n=1 max=25")
  void convertToInt_acceptCorrect7() {
    // N = 1
    int p = 5;
    int s = 2;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Collections.singletonList(
        new ListVector<Integer>(0, 0))));
    assertEquals(7, GFieldCacheUtils.convertToNumber(p, s, Collections.singletonList(
        new ListVector<Integer>(2, 1))));
    assertEquals(24, GFieldCacheUtils.convertToNumber(p, s, Collections.singletonList(
        new ListVector<Integer>(4, 4))));
  }

  @Test
  @DisplayName("Converts to Number p=5 s=2 n=2 max=625")
  void convertToInt_acceptCorrect6() {
    // N = 2
    int p = 5;
    int s = 2;

    assertEquals(0, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 0),
        new ListVector<Integer>(0, 0))));
    assertEquals(95, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(0, 4),
        new ListVector<Integer>(3, 0))));
    assertEquals(624, GFieldCacheUtils.convertToNumber(p, s, Arrays.asList(
        new ListVector<Integer>(4, 4),
        new ListVector<Integer>(4, 4))));
  }

  @Test
  @DisplayName("Cached vector for all numbers of Gfield(p=2, s=2, n=2)")
  void get_cached_4() {
    int p = 2;
    int s = 2;
    int n = 2;

    assertGFieldCacheByNumber(p, s, n);
  }

  private void assertGFieldCacheByNumber(int p, int s, int n) {
    Vector3D<Integer> params = new Vector3D<Integer>(p, s, n);
    int num = 1;

    List<Vector<Integer>> result = GFieldCacheUtils.get(params, num);

    assertNotNull(result);
    for (int i = 0; i < pow(p, s * n); i++) {
      assertEquals(i, GFieldCacheUtils.convertToNumber(p, s, GFieldCacheUtils.get(params, i)));
    }
  }

  @Test
  @DisplayName("Cached vector for all numbers of Gfield(p=2, s=3, n=3)")
  void get_cached_5() {
    int p = 2;
    int s = 3;
    int n = 3;

    assertGFieldCacheByNumber(p, s, n);
  }

  @Test
  @DisplayName("Cached vector for all numbers of Gfield(p=2, s=4, n=5)")
  void get_cached_6() {
    int p = 2;
    int s = 3;
    int n = 5;

    assertGFieldCacheByNumber(p, s, n);
  }
}