package com.gebond.ip.math.commons.util;

import static com.gebond.ip.math.commons.util.ArrayUtil.copyOf;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Created by Gleb on 27.01.2018.
 */
@DisplayName("Array util tests")
class ArrayUtilTest {

  @Nested
  @DisplayName("2D arrays")
  class Array2D {

    @Test
    @DisplayName("2D array copied")
    void arrayCopyTest() {
      double[][] source = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};

      double[][] target = copyOf(source);

      assertEquals(source.length, target.length);
      assertEquals(source[0].length, target[0].length);
      assertArrayEquals(source, target);
    }

    @Test
    @DisplayName("not changed after source's changes")
    void arrayCopy_changeSourceArray() {
      double[][] source = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};
      double[][] source_copy = new double[][]{{1.0, 2.0, 3.0}, {3.0, 4.0, 6.0}};

      double[][] target = copyOf(source);
      // corruption of source array
      source[1][0] = -10.0;
      source[0][1] = 100.0;

      assertEquals(source.length, target.length);
      assertEquals(source[0].length, target[0].length);
      assertArrayEquals(source_copy, target);
    }
  }
}