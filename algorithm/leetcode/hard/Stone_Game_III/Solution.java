package algorithm.leetcode.hard.Stone_Game_III;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 7};
        System.out.println(stoneGameIII(values));
    }
    public static String stoneGameIII(int[] stoneValue) {
        int[] presum = Arrays.copyOf(stoneValue, stoneValue.length);
        for (int i = presum.length - 2; i >= 0; i--) {
            presum[i] = presum[i + 1] + stoneValue[i];
        }

        int pts = dp(0, presum, new Integer[stoneValue.length]);

        return pts > 0 ? "Alice" : pts < 0 ? "Bob" : "Tie";
    }

    private static int dp(int i, int[] presum, Integer[] memo) {
        if (i >= presum.length - 3) {
            return presum[i];
        }

        if (memo[i] != null) return memo[i];

        int cur = 0, max = Integer.MIN_VALUE;
        for (int x = 1; x <= 3; x++) {
            cur = presum[i] - presum[i + x];
            max = Math.max(max, cur - dp(i + x, presum, memo));
        }

        return memo[i] = max;
    }
}
