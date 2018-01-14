package com.gebond.ip.math.func.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gebond.ip.math.func.util.Numerations.paley;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Gleb on 18.10.2017.
 */
@DisplayName("numerations tests")
public class NumerationsTest {

    @Nested
    @DisplayName("paley")
    class PaleyTests {

        @Test
        @DisplayName("when n = 2k; k ~ Z+")
        public void paley_poweroftwo() {
            int[] res = paley(1);
            assertArrayEquals(new int[]{1}, res);

            res = paley(2);
            assertArrayEquals(new int[]{0, 1}, res);

            res = paley(8);
            assertArrayEquals(new int[]{0, 0, 0, 1}, res);
        }

        @Test
        @DisplayName("when n = 2k + 1; k ~ Z+")
        public void paley_no_poweroftwo() {
            int[] res = paley(3);
            assertArrayEquals(new int[]{1, 1}, res);

            res = paley(6);
            assertArrayEquals(new int[]{0, 1, 1}, res);

            res = paley(13);
            assertArrayEquals(new int[]{1, 0, 1, 1}, res);
        }
    }
}