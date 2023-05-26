package algorithm.leetcode.hard.Maximize_Score_After_N_Operations;

public class Solution {
    Integer[] dp;
    public int maxScore(int[] nums) {
        dp = new Integer[1 << nums.length];

        return dfs(0, 1, nums);
    }

    private int dfs(int check, int n, int[] nums) {
        if (dp[check] != null) return dp[check];

        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((check & (1 << i)) > 0) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if ((check & (1 << j)) > 0) continue;
                max = Math.max(max, n * gcd(nums[i], nums[j]) + dfs(check | (1 << i) | (1 << j), n + 1, nums));
            }
        }

        return dp[check] = max;
    }

    private int gcd(int x, int y) {
        if (x < y) return gcd(y, x);

        int r = x % y;

        if (r == 0) return y;
        else return gcd(y, r);
    }
}
