package com.gebond.ip.math.func.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.gebond.ip.math.func.util.Functions.haart;
import static com.gebond.ip.math.func.util.Functions.rademacher;
import static com.gebond.ip.math.func.util.Functions.walsh;
import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by Gleb on 18.10.2017.
 */
@DisplayName("core math function tests")
public class FunctionsTest {

    @Nested
    @DisplayName("rademacher")
    class RademacherTests {

        @Test
        @DisplayName("when k < 0 throws exception")
        public void rademacher_exception() {
            assertThrows(IllegalArgumentException.class,
                    () -> rademacher(-1, 1.0));
        }

        @Test
        @DisplayName("when x ~ [0- .. 1+]")
        public void rademacher_0() {
            assertEquals(-1.0, rademacher(-0.01));
            assertEquals(1.0, rademacher(0));
            assertEquals(1.0, rademacher(0.01));
            assertEquals(1.0, rademacher(0.25));
            assertEquals(1.0, rademacher(0.49));
            assertEquals(1.0, rademacher(1.0));

            assertEquals(-1.0, rademacher(0.5));
            assertEquals(-1.0, rademacher(0.51));
            assertEquals(-1.0, rademacher(0.75));
            assertEquals(-1.0, rademacher(0.99));
            assertEquals(1.0, rademacher(1.0));
            assertEquals(1.0, rademacher(1.01));
        }

        @Test
        @DisplayName("when x ~ [-t- .. t+1+] t >= 1")
        public void rademacher_0_period() {
            assertEquals(-1.0, rademacher(0.99));
            assertEquals(1.0, rademacher(1));
            assertEquals(1.0, rademacher(1.01));
            assertEquals(1.0, rademacher(1.25));
            assertEquals(1.0, rademacher(1.49));
            assertEquals(1.0, rademacher(2.0));

            assertEquals(-1.0, rademacher(1.5));
            assertEquals(-1.0, rademacher(1.51));
            assertEquals(-1.0, rademacher(1.75));
            assertEquals(-1.0, rademacher(1.99));
            assertEquals(1.0, rademacher(2.0));
            assertEquals(1.0, rademacher(2.01));
        }

        @Test
        @DisplayName("when x ~ [t-1- .. t+] t <= 0")
        public void rademacher_0_neg_period() {
            assertEquals(-1.0, rademacher(-1.01));
            assertEquals(1.0, rademacher(-1));
            assertEquals(1.0, rademacher(-0.99));
            assertEquals(-1.0, rademacher(-0.49));

            assertEquals(-1.0, rademacher(-0.5));
            assertEquals(1.0, rademacher(-0.51));
            assertEquals(1.0, rademacher(0));
            assertEquals(1.0, rademacher(0.01));
        }

        @Test
        @DisplayName("when x ~ [t-1- .. t+] t <= 0; k = 1")
        public void rademacher_1() {
            assertEquals(1.0, rademacher(1, -0.5));
            assertEquals(1.0, rademacher(1, -0.49));
            assertEquals(1.0, rademacher(1, -0.26));
            assertEquals(1.0, rademacher(1, 0.0));
            assertEquals(1.0, rademacher(1, 0.01));
            assertEquals(1.0, rademacher(1, 0.15));
            assertEquals(1.0, rademacher(1, 0.24));
            assertEquals(1.0, rademacher(1, 0.5));
            assertEquals(1.0, rademacher(1, 0.65));
            assertEquals(1.0, rademacher(1, 0.74));
            assertEquals(1.0, rademacher(1, 1));
            assertEquals(1.0, rademacher(1, 1.01));

            assertEquals(-1.0, rademacher(1, -0.25));
            assertEquals(-1.0, rademacher(1, -0.24));
            assertEquals(-1.0, rademacher(1, -0.01));
            assertEquals(-1.0, rademacher(1, 0.25));
            assertEquals(-1.0, rademacher(1, 0.26));
            assertEquals(-1.0, rademacher(1, 0.49));
            assertEquals(-1.0, rademacher(1, 0.75));
            assertEquals(-1.0, rademacher(1, 0.76));
            assertEquals(-1.0, rademacher(1, 0.85));
            assertEquals(-1.0, rademacher(1, 0.99));
        }

        @Test
        @DisplayName("when x ~ R; k ~ Z")
        public void rademacher_kth() {
            assertEquals(1.0, rademacher(3.01));
            assertEquals(1.0, rademacher(1, -1.49));
            assertEquals(1.0, rademacher(1, -2.99));
            assertEquals(1.0, rademacher(1, 3.01));
            assertEquals(1.0, rademacher(1, 2.51));
            assertEquals(1.0, rademacher(2, -1.49));
            assertEquals(1.0, rademacher(2, -0.74));
            assertEquals(1.0, rademacher(2, 0.76));
            assertEquals(1.0, rademacher(2, 1.26));

            assertEquals(-1.0, rademacher(-2.01));
            assertEquals(-1.0, rademacher(1, 0.25));
            assertEquals(-1.0, rademacher(1, 0.26));
            assertEquals(-1.0, rademacher(1, 0.49));
            assertEquals(-1.0, rademacher(2, 1.24));
            assertEquals(-1.0, rademacher(2, 0.99));
            assertEquals(-1.0, rademacher(2, -0.51));
            assertEquals(-1.0, rademacher(2, -1.51));
        }
    }

    @Nested
    @DisplayName("walsh")
    class WalshTests {

        @Test
        @DisplayName("when n < 0 throws exception")
        public void walsh_exception() {
            assertThrows(IllegalArgumentException.class,
                    () -> walsh(-1, 1.0));
        }

        @Test
        @DisplayName("when n = 0; x ~ R")
        public void walsh_0() {
            assertEquals(1.0, walsh(0, -1.5));
            assertEquals(1.0, walsh(0, 0.5));
            assertEquals(1.0, walsh(0, 1.5));
        }

        @Test
        @DisplayName("when n = 1; x ~ (0 .. 1)")
        public void walsh_1() {
            assertEquals(1.0, walsh(1, 0.01));
            assertEquals(1.0, walsh(1, 0.25));
            assertEquals(-1.0, walsh(1, 0.75));
            assertEquals(-1.0, walsh(1, 0.99));
        }

        @Test
        @DisplayName("when n = 1; x ~ R")
        public void walsh_1_period() {
            assertEquals(1.0, walsh(1, 1.01));
            assertEquals(1.0, walsh(1, 1.25));
            assertEquals(1.0, walsh(1, -0.75));
            assertEquals(1.0, walsh(1, -0.99));

            assertEquals(-1.0, walsh(1, 1.51));
            assertEquals(-1.0, walsh(1, 1.99));
            assertEquals(-1.0, walsh(1, -0.25));
            assertEquals(-1.0, walsh(1, -0.01));
        }

        @Test
        @DisplayName("when n = 2; x ~ [0- .. 1)")
        public void walsh_2() {
            assertEquals(-1.0, walsh(2, -0.01));
            assertEquals(1.0, walsh(2, 0));
            assertEquals(1.0, walsh(2, 0.01));
            assertEquals(1.0, walsh(2, 0.24));
            assertEquals(-1.0, walsh(2, 0.25));
            assertEquals(-1.0, walsh(2, 0.26));
            assertEquals(-1.0, walsh(2, 0.49));

            assertEquals(1.0, walsh(2, 0.5));
            assertEquals(1.0, walsh(2, 0.51));
            assertEquals(1.0, walsh(2, 0.74));
            assertEquals(-1.0, walsh(2, 0.75));
            assertEquals(-1.0, walsh(2, 0.76));
            assertEquals(-1.0, walsh(2, 0.99));
        }

        @Test
        @DisplayName("when n = 3; x ~ [0- .. 1+]")
        public void walsh_3() {
            assertEquals(1.0, walsh(3, -0.01));
            assertEquals(1.0, walsh(3, 0));
            assertEquals(1.0, walsh(3, 0.01));
            assertEquals(1.0, walsh(3, 0.24));
            assertEquals(1.0, walsh(3, 0.75));
            assertEquals(1.0, walsh(3, 0.76));
            assertEquals(1.0, walsh(3, 0.99));
            assertEquals(1.0, walsh(3, 1));
            assertEquals(1.0, walsh(3, 1.01));

            assertEquals(-1.0, walsh(3, 0.25));
            assertEquals(-1.0, walsh(3, 0.26));
            assertEquals(-1.0, walsh(3, 0.5));
            assertEquals(-1.0, walsh(3, 0.51));
            assertEquals(-1.0, walsh(3, 0.74));
        }
    }

    @Nested
    @DisplayName("haart")
    class HaartTests {

        @Test
        @DisplayName("when n < 0 throws exception")
        public void haart_exception() {
            assertThrows(IllegalArgumentException.class,
                    () -> haart(-1, 1.0));
        }

        @Test
        @DisplayName("when k,m < 0 or wrong m throws exception")
        public void haart_exception_2() {
            assertThrows(IllegalArgumentException.class,
                    () -> haart(1, -1, 1.0));

            assertThrows(IllegalArgumentException.class,
                    () -> haart(-1, 1, 1.0));

            assertThrows(IllegalArgumentException.class,
                    () -> haart(1, 10, 1.0));
        }

        @Test
        @DisplayName("when m = 0; k = 0; x ~ R")
        public void haart_0() {
            assertEquals(1.0, haart(0, 0, -1.5));
            assertEquals(1.0, haart(0, 0, 0.25));
            assertEquals(1.0, haart(0, 0, 0.5));
            assertEquals(1.0, haart(0, 0, 0.75));
            assertEquals(1.0, haart(0, 0, 1.5));
        }
        
        @Test
        @DisplayName("when m = 0; k = 1; x ~ [0 .. 1-)")
        public void haart_0_1() {
            assertEquals(1.0, haart(0, 1, 0.00));
            assertEquals(1.0, haart(0, 1, 0.01));
            assertEquals(1.0, haart(0, 1, 0.49));
            assertEquals(-1.0, haart(0, 1, 0.5));
            assertEquals(-1.0, haart(0, 1, 0.51));
        }
        
        @Test
        @DisplayName("when k = m = 1; x ~ [0 .. 1-)")
        public void haart_1_1() {
            assertEquals(1.0, haart(1, 1, 0.00));
            assertEquals(1.0, haart(1, 1, 0.01));
            assertEquals(1.0, haart(1, 1, 0.24));
            assertEquals(-1.0, haart(1, 1, 0.25));
            assertEquals(-1.0, haart(1, 1, 0.26));
            assertEquals(-1.0, haart(1, 1, 0.49));
            assertEquals(0.0, haart(1, 1, 0.51));
            assertEquals(0.0, haart(1, 1, 0.74));
            assertEquals(0.0, haart(1, 1, 0.99));
        }
        
        @Test
        @DisplayName("when m = 1; k = 2; x ~ [0 .. 1-)")
        public void haart_1_2() {
            assertEquals(0.0, haart(1, 2, 0.00));
            assertEquals(0.0, haart(1, 2, 0.01));
            assertEquals(0.0, haart(1, 2, 0.24));
            assertEquals(0.0, haart(1, 2, 0.25));
            assertEquals(0.0, haart(1, 2, 0.26));
            assertEquals(0.0, haart(1, 2, 0.49));
            assertEquals(1.0, haart(1, 2, 0.50));
            assertEquals(1.0, haart(1, 2, 0.51));
            assertEquals(1.0, haart(1, 2, 0.74));
            assertEquals(-1.0, haart(1, 2, 0.75));
            assertEquals(-1.0, haart(1, 2, 0.99));
        }
        
        @Test
        @DisplayName("when m = 2; k = 1; x ~ [0 .. 1-)")
        public void haart_2() {
            assertEquals(1.0, haart(2, 1, 0.00));
            assertEquals(1.0, haart(2, 1, 0.01));
            assertEquals(1.0, haart(2, 1, 0.124));
            assertEquals(-1.0, haart(2, 1, 0.125));
            assertEquals(-1.0, haart(2, 1, 0.126));
            assertEquals(-1.0, haart(2, 1, 0.24));
            assertEquals(0.0, haart(2, 1, 0.25));
            assertEquals(0.0, haart(2, 1, 0.51));
            assertEquals(0.0, haart(2, 1, 0.99));
        }

        @Test
        @DisplayName("when n ~ Z+; x ~ [0 .. 1-)")
        public void haart_n() {
            assertEquals(1.0, haart(0, 0.00));
            assertEquals(1.0, haart(1, 0.49));
            assertEquals(-1.0, haart(1, 0.5));

            assertEquals(1.0, haart(4, 0.00));
            assertEquals(1.0, haart(4, 0.01));
            assertEquals(1.0, haart(4, 0.124));
            assertEquals(-1.0, haart(4, 0.125));
            assertEquals(-1.0, haart(4, 0.126));
            assertEquals(-1.0, haart(4, 0.24));
            assertEquals(0.0, haart(4, 0.25));
            assertEquals(0.0, haart(4, 0.51));
            assertEquals(0.0, haart(4, 0.99));
        }
        
        @Test
        @DisplayName("when n >> 1")
        public void haart_big_n() {
            assertEquals(0.0, haart(7, 0.50));
            assertEquals(1.0, haart(7, 0.874));
            assertEquals(-1.0, haart(7, 0.99));
        }
    }
}