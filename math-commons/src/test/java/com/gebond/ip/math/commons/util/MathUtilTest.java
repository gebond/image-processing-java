package com.gebond.ip.math.commons.util;

import static com.gebond.ip.math.commons.util.MathUtil.divideNumber;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Created by Gleb on 22.10.2017.
 */
@DisplayName("core math util tests")
class MathUtilTest {

  @Nested
  @DisplayName("divide number into primes")
  class RademacherTests {

    @Test
    @DisplayName("when n < 8 throws exception")
    public void divide_exception() {
      assertThrows(IllegalArgumentException.class,
          () -> divideNumber(7));
      assertThrows(IllegalArgumentException.class,
          () -> divideNumber(-1));
    }

    @Test
    @DisplayName("when n = 10 (even)")
    public void divide_2k() {
      assertArrayEquals(new int[]{2, 5}, divideNumber(10));
    }

    @Test
    @DisplayName("when n = 2000 (even)")
    public void divide_2k_2() {
      assertArrayEquals(new int[]{2, 1000}, divideNumber(2000));
    }

    @Test
    @DisplayName("when n = 33 (step 1)")
    public void divide_n_1() {
      assertArrayEquals(new int[]{3, 11}, divideNumber(33));
    }

    @Test
    @DisplayName("when n = 11 (prime)")
    public void divide_n_3() {
      assertArrayEquals(new int[]{}, divideNumber(11));
    }

    @Test
    @DisplayName("when n = 65 (step 2)")
    @Disabled("todo implement logic")
    public void divide_n_2() {
      assertArrayEquals(new int[]{5, 13}, divideNumber(65));
    }
  }
}