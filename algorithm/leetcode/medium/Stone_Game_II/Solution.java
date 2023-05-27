package algorithm.leetcode.medium.Stone_Game_II;

import java.util.Arrays;

public class Solution {
    /**
     * [일반 dp 풀이]
     *
     */
    Integer[][][] dp;
    public int stoneGameII(int[] piles) {
        dp = new Integer[2][piles.length][piles.length / 2 + 2];

        return dfs(true, 0, 1, piles);
    }

    private int dfs(boolean Alice, int i, int M, int[] piles) {
        if (i == piles.length) return 0;

        int p = Alice ? 0 : 1;
        if (dp[p][i][M] != null) return dp[p][i][M];

        int stones = Alice ? 0 : Integer.MAX_VALUE;
        int cur = 0;
        for (int X = 1; X <= 2*M; X++) {
            if (i + X > piles.length) break;

            cur += piles[i + X - 1];
            if (Alice) {
                stones = Math.max(stones, cur + dfs(!Alice, i + X, Math.max(M, X), piles));
            } else {
                stones = Math.min(stones, dfs(!Alice, i + X, Math.max(M, X), piles));
            }
        }

        return dp[p][i][M] = stones;
    }

    /**
     * [Prefix Sum을 활용한 dp 풀이]
     *
     */
    public int stoneGameII_prefixSum(int[] piles) {
        int[] presum =  Arrays.copyOf(piles, piles.length);
        for (int i = presum.length - 2; i >= 0; i--) presum[i] += presum[i + 1];
        return dfs(presum, 1, 0, new int[piles.length][piles.length]);
    }

    private int dfs(int[] presum, int m, int p, int[][] memo) {
        if (p + 2 * m >= presum.length) { // last player takes all
            return presum[p];
        }

        if (memo[p][m] > 0) return memo[p][m];
        int res = 0, take = 0;
        for (int i = 1; i <= 2 * m; i++) {
            // take = 이번 차례에 가져가는 stone의 개수
            take = presum[p] - presum[p + i];
            // "take + 남은 stone 중에서 상대방이 가져가고 남은 것"의 최대값
            res = Math.max(res, take + presum[p + i] - dfs(presum, Math.max(i, m), p + i, memo));
        }
        memo[p][m] = res;
        return res;
    }
}
