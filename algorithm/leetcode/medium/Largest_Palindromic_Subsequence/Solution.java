package algorithm.leetcode.medium.Largest_Palindromic_Subsequence;

public class Solution {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));;
    }
    private static int n;
    private static char[] c;
    private static Integer[][] mem;
    public static int longestPalindromeSubseq(String s) {
        n = s.length();
        c = s.toCharArray();
        mem = new Integer[n][n];

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (n % 2 != 0) {
                ans = Math.max(ans, dp(i, i));
            } else {
                ans = Math.max(ans, dp(i, i + 1));
            }
        }

        return ans;
    }

    private static int dp(int i, int j) {
        if (i < 0 || j >= n) {
            return 0;
        }

        if (mem[i][j] != null)
            return mem[i][j];

        if (c[i] == c[j]) {
            int length = i == j ? 1 : 2;
            mem[i][j] = length + dp(i - 1, j + 1);
        } else {
            mem[i][j] = Math.max(dp(i - 1, j), dp(i, j + 1));
        }

        return mem[i][j];
    }
}
