package gebond.ip.math.common.util;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static gebond.ip.math.common.util.MathUtil.divideNumber;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            int[] result = divideNumber(10);

            int[] expected = new int[]{2, 5};

            assertArrayEquals(expected, result);
        }

        @Test
        @DisplayName("when n = 2000 (even)")
        public void divide_2k_2() {
            int[] result = divideNumber(2000);

            int[] expected = new int[]{2, 1000};

            assertArrayEquals(expected, result);
        }

        @Test
        @DisplayName("when n = 33 (step 1)")
        public void divide_n_1() {
            int[] result = divideNumber(33);

            int[] expected = new int[]{3, 11};

            assertArrayEquals(expected, result);
        }

        @Test
        @DisplayName("when n = 11 (prime)")
        public void divide_n_3() {
            int[] result = divideNumber(11);

            int[] expected = new int[]{};

            assertArrayEquals(expected, result);
        }

        @Test
        @DisplayName("when n = 65 (step 2)")
        @Disabled("todo implement logic")
        public void divide_n_2() {
            int[] result = divideNumber(65);

            int[] expected = new int[]{5, 13};

            assertArrayEquals(expected, result);
        }

    }

}