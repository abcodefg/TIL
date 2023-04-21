package algorithm.leetcode.hard.Profitable_Schemes;

public class Solution {
    private int minProfit, len;
    private int mod;
    private int[] group, profit;
    private Integer[][][] mem;
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        this.minProfit = minProfit;
        this.group = group;
        this.profit = profit;
        len = group.length;
        mod = (int) Math.pow(10, 9) + 7;
        mem = new Integer[len][n + 1][100 * len + 1];

        return recur(0, n, 0);
    }

    private int recur(int i, int n, int sum) {
        if (i == len) {
            return (sum >= minProfit) ? 1 : 0;
        }

        if (mem[i][n][sum] != null)
            return mem[i][n][sum];

        mem[i][n][sum] = recur(i + 1, n, sum);
        if (n - group[i] >= 0) {
            mem[i][n][sum] += recur(i + 1, n - group[i], sum + profit[i]) % mod;
        }
        return mem[i][n][sum];
    }
}
