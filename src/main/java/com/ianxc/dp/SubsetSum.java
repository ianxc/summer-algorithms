package com.ianxc.dp;

import com.ianxc.core.CoreUtil;

import java.util.Arrays;

import static com.ianxc.core.CoreUtil.*;

public class SubsetSum {
    private static final int NIL_PARENT = -1;

    public static SubsetSumAnswer subsetSum(int[] s, int targetSum) {
        var n = s.length;
        var isSum = new boolean[n + 1][targetSum + 1];
        var parent = new int[n + 1][targetSum + 1];

        isSum[0][0] = true;
        parent[0][0] = NIL_PARENT;

        for (var partSum = 1; partSum <= targetSum; partSum++) {
            isSum[0][partSum] = false;
            parent[0][partSum] = NIL_PARENT;
        }

        dbg(() -> {
            println("subset sum:");
            println("s: " + Arrays.toString(s));
        });

        for (var row = 1; row <= n; row++) {
            for (var partSum = 0; partSum <= targetSum; partSum++) {
                isSum[row][partSum] = isSum[row - 1][partSum];
                parent[row][partSum] = NIL_PARENT;

                /*
                 * Here, i == row and j == partSum.
                 * Use s[i-1] as s does NOT have s[0]=0.
                 * In other words, s[i-1] is the value of s_i (math notation: 1-indexed)
                 * that is to be added, which makes sense.
                 * j means the current goal sum.
                 *
                 * Hence, the first condition checks that j is at least as large as the
                 * s_i (i.e. s[i-1]) contribution to be added. This check will fail for
                 * cells not on the separating edge e.g. (i, j) = (1, 0); though (1, 0) will
                 * have had is_sum copied down from the cell immediately above it in the
                 * previous lines.
                 *
                 * The second condition checks that its predecessor cell, which must be
                 * in the previous row, and have column = (current/desired sum j - s[i-1] which
                 * is the contribution to be added), had a valid knapsack/subset sum.
                 */
                if (partSum >= s[row - 1] && isSum[row - 1][partSum - s[row - 1]]) {
                    int row_ = row;
                    int partSum_ = partSum;
                    dbg(() -> System.out.printf("row=%2d  partSum=%2d  s[row-1]=%2d  " +
                            "partSum-s[row-1]=%2d  check is_sum[%2d][%2d]\n",
                            row_, partSum_, s[row_ - 1], partSum_ - s[row_ - 1], row_ - 1,
                            partSum_ - s[row_ - 1]));

                    isSum[row][partSum] = true;
                    parent[row][partSum] = partSum - s[row - 1];
                }
            }
        }

        return new SubsetSumAnswer(isSum[n][targetSum], isSum, parent);
    }

    public static void reportAnswer(SubsetSumAnswer a) {
        // derive targetSum and number of elements in s from answer structures
        var sn = a.isSum.length - 1;
        var targetSum = a.isSum[0].length - 1;
        println("start reporting answer");
        reportAnswer(sn, targetSum, a);
        println();
        println("end reporting answer");
    }

    /**
     * Reports answers going forward.
     */
    private static void reportAnswer(int sn, int remainingSum, SubsetSumAnswer a) {
        if (remainingSum == 0) {
            return;
        }
        var remainingWithoutCurrent = a.parent[sn][remainingSum];
        if (remainingWithoutCurrent == NIL_PARENT) {
            reportAnswer(sn - 1, remainingSum, a);
        } else {
            reportAnswer(sn - 1, remainingWithoutCurrent, a);
            System.out.printf("%2d", remainingSum - remainingWithoutCurrent);
        }
    }

    public record SubsetSumAnswer(
            boolean found,
            boolean[][] isSum,
            int[][] parent) {
        public SubsetSumAnswer {
            require(isSum.length == parent.length,
                    () -> "isSum should have same number of rows as parent");
        }

        @Override
        public String toString() {
            var sb = new StringBuilder();
            sb.append("found: ")
                    .append(found)
                    .append('\n');

            sb.append("isSum:\n");
            for (var row : isSum) {
                for (var cell : row) {
                    sb.append(String.format("%3d", cell ? 1 : 0));
                }
                sb.append('\n');
            }

            sb.append("parent:\n");
            for (int rowIdx = 0; rowIdx < isSum.length; rowIdx++) {
                for (var cell : parent[rowIdx]) {
                    sb.append(String.format("%3d", cell));
                }
                if (!CoreUtil.isLastIndex(isSum, rowIdx)) {
                    sb.append('\n');
                }
            }
            return sb.toString();
        }
    }

}
