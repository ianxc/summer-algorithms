package com.ianxc.dp;

import org.junit.jupiter.api.Test;

import static com.ianxc.core.CoreUtil.println;

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
