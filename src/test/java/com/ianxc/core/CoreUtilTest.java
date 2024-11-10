package com.ianxc.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.ianxc.core.CoreUtil.println;

public class CoreUtilTest {
    @Test
    void println_charArray() {
        char[] xs = {'a', 'b', 'c'};
        println(xs);
    }

    @Test
    void println_array2D() {
        int[][] xs = {{1, 2, 3}, {7, 2, 1}};
        println(xs);
        println(Arrays.toString(xs));
        println(Arrays.deepToString(xs));
        for (var x : xs) {
            println(Arrays.toString(x));
        }
    }
}
