package algorithm.leetcode.hard.Minimum_Cost_to_Cut_a_Stick;

import java.util.Arrays;

public class Solution {
    /**
     * [TOP-DOWN DP]
     * 막대의 양 끝 지점과 cuts를 순서대로 담은 stick 배열을 만든다.
     * 왼쪽 끝과 오른쪽 끝을 지정하여 그 사이의 cuts를 모두 잘랐을 때의 최소 비용을 dp 배열에 담는다.
     *
     * Time : O(n^3)
     * Space : O(n^2) (dp 배열과 재귀 콜 스택)
     * Runtime : 22 ms (Beats 72.63%)
     * Memory : 41.9 MB (Beats 83.86%)
     */
    public int minCost_TopDown(int n, int[] cuts) {

        int[] stick = new int[cuts.length + 2];
        int len = stick.length;

        stick[0] = 0;
        stick[len - 1] = n;
        for (int i = 0; i < cuts.length; i++) {
            stick[i + 1] = cuts[i];
        }
        Arrays.sort(stick);

        return dfs(0, len - 1, stick, new int[len - 1][len]);
    }

    // dp[l][r]는 stick의 l부터 r 사이의 cuts를 모두 잘랐을 때의 최소 비용
    private int dfs(int l, int r, int[] stick, int[][] dp) {
        if (r - l == 1) return 0;
        if (dp[l][r] > 0) return dp[l][r];

        int cur = stick[r] - stick[l];
        int min = Integer.MAX_VALUE;
        for (int i = l + 1; i < r; ++i) {
            min = Math.min(min, dfs(l, i, stick, dp) + dfs(i, r, stick, dp));
        }

        return dp[l][r] = cur + min;
    }

    /**
     * [BOTTOM-UP DP]
     * 첫 번째 풀이의 논리를 Bottom-Up 방식으로 구현한 것이다.
     * Tabulation을 통한 dp의 풀이가 대개 그렇듯 상대적으로 덜 직관적인 대신 실행시간은 짧다.
     */
    public int minCost_BottomUp(int n, int[] cuts) {
        int len = cuts.length;
        int[] stick = new int[len+2];
        // dp[i][j]는 stick의 i부터 j 사이의 cuts를 모두 잘랐을 때의 최소 비용
        int[][] dp = new int[len+2][len+2];
        stick[0] = 0; stick[len+1] = n;

        for(int i = 1; i <= len; i++) stick[i] = cuts[i-1];
        Arrays.sort(stick);

        for(int i = len; i >= 1; i--){
            for(int j = 1; j <= len; j++){
                if(i > j)continue;
                int mini = Integer.MAX_VALUE;
                for(int ind = i; ind <= j; ind++){
                    int cost = stick[j+1] - stick[i-1] + dp[i][ind-1] + dp[ind+1][j];
                    mini = Math.min(mini, cost);
                }

                dp[i][j] = mini;
            }
        }
        return dp[1][len];
    }
}
