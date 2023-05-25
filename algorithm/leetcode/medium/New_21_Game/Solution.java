package algorithm.leetcode.medium.New_21_Game;

public class Solution {
    /**
     * [DFS를 활용한 DP 풀이]
     *
     * Time : O(k * maxPts) (재귀 호출 * 호출 1번 당 반복문)
     * Space : O(k) (dp 배열, 재귀 호출)
     * Runtime : 시간 초과
     */
    public double new21Game_DFS(int n, int k, int maxPts) {
        if (n - k + 1 >= maxPts) return 1.0;
        dp = new Double[k];

        return dfs(n, k, maxPts, 0);
    }

    private Double[] dp;
    private double dfs(int n, int k, int maxPts, int pts) {
        if (pts >= k) {
            if (pts <= n) return 1;
            else return 0;
        }

        if (dp[pts] != null) return dp[pts];

        double prob = 0;
        for (int i = 1; i <= maxPts; i++) {
            prob += dfs(n, k, maxPts, pts + i);
        }

        return dp[pts] = prob / maxPts;
    }

    /**
     * [Sliding Window를 활용한 풀이]
     * Time : O(n) = O(n - k + k) (for문의 시간 복잡도 + while문의 시간 복잡도)
     * Space : O(k + maxPts)
     * Runtime : 0 ms (100%)
     * Memory : 40.4 MB (89.85%)
     */
    public double new21Game_SlidingWindow(int n, int k, int maxPts) {
        if (n - k + 1 >= maxPts) return 1.0;

        double[] dp = new double[k + maxPts];
        double sum = n - k + 1;
        for (int i = k; i <= n; i++) {
            dp[i] = 1.0;
        }
        int l = k - 1, r = k + maxPts - 1;

        while (l >= 0) {
            dp[l] = sum / (double) maxPts;
            sum += dp[l];
            sum -= dp[r];
            l--;
            r--;
        }

        return dp[0];
    }
}
