package algorithm.leetcode.hard.Maximum_Values_of_K_Coins_From_Piles;

import java.util.List;

public class Solution {
    /**
     * [TOP-DOWN DP]
     *
     * Time : O(m * k) (m = sum(piles[i].length) <= 2000)
     * Space : O(n * k) (n은 piles의 길이)
     * Runtime : 210 ms (Beats 79.60%)
     * Memory : 57.5 MB (Beats 16.57%)
     */
    private int n;
    private Integer[][] mem;
    private List<List<Integer>> p;
    public int maxValueOfCoins1(List<List<Integer>> piles, int k) {
        n = piles.size();
        mem = new Integer[n][k + 1];
        p = piles;

        return recur(0, k);
    }

    private int recur(int i, int k) {
        if (i == n) return 0;

        if (mem[i][k] != null)
            return mem[i][k];

        int res = recur(i + 1, k);
        int cur = 0;
        for (int j = 0; j < Math.min(p.get(i).size(), k); j++) {
            cur += p.get(i).get(j);
            res = Math.max(res, cur + recur(i + 1, k - j - 1));
        }

        return mem[i][k] = res;
    }

    /**
     * [BOTTOM-UP DP]
     *
     * Time : O(m * k)
     * Space : O(k)
     * Runtime : 29 ms (Beats 79.60%)
     * Memory : 57.5 MB (Beats 16.57%)
     */
    public int maxValueOfCoins2(List<List<Integer>> piles, int k) {
        int[] mv = new int[k + 1];
        int[] pileSum = new int[k + 1];

        for (List<Integer> pile : piles) {
            int n = Math.min(k, pile.size());
            int sum = 0;

            for (int i = 1; i <= n; i++) {
                pileSum[i] = sum += pile.get(i - 1);
            }

            for (int i = k; i > 0; i--) {
                int max = 0;

                for (int j = Math.min(i, n); j >= 0; j--) {
                    max = Math.max(max, pileSum[j] + mv[i - j]);
                }

                mv[i] = max;
            }
        }

        return mv[k];
    }
}
