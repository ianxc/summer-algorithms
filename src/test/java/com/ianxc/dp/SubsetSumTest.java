package com.ianxc.dp;

import static com.ianxc.core.CoreUtil.println;

import org.junit.jupiter.api.Test;

public class SubsetSumTest {
    @Test
    void testSubsetSum() {
        int[] s = { 1, 2, 4, 8 };
        var target = 11;
        var answer = SubsetSum.subsetSum(s, target);
        println(answer);

        SubsetSum.reportAnswer(answer);
    }
}
