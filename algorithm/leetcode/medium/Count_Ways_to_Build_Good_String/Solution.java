package algorithm.leetcode.medium.Count_Ways_to_Build_Good_String;

public class Solution {
    private final int mod = (int) Math.pow(10, 9) + 7;
    private Integer[] dp;
    public int countGoodStrings(int low, int high, int zero, int one) {
        dp = new Integer[high + 1];
        int ans = 0;

        return dfs(0, low, high, zero, one);
    }

    private int dfs(int len, int low, int high, int zero, int one) {
        if (len > high) return 0;
        if (dp[len] != null) return dp[len];

        dp[len] = len >= low ? 1 : 0;

        dp[len] += dfs(len + zero, low, high, zero, one) + dfs(len + one, low, high, zero, one);
        return dp[len] % mod;
    }
}
