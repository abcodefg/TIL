package algorithm.leetcode.medium.Longest_Common_Subsequence;

public class Solution {
    /**
     *
     * Time : O(m * n)
     * Space : O(m * n)
     * Runtime : 36 ms (23.15%)
     * Memory : 60.3 MB (5.8%)
     */
    private Integer[][] mem;
    private char[] t1, t2;
    public int longestCommonSubsequence(String text1, String text2) {
        int rows = text1.length(), cols = text2.length();
        mem = new Integer[rows + 1][cols + 1];
        t1 = text1.toCharArray();
        t2 = text2.toCharArray();
        for (int i = 0; i <= cols; i++) {
            mem[rows][i] = 0;
        }
        for (int i = 0; i <= rows; i++) {
            mem[i][cols] = 0;
        }

        return dp(0, 0);
    }

    private int dp(int i, int j) {
        if (mem[i][j] != null)
            return mem[i][j];


        if (t1[i] == t2[j]) {
            mem[i][j] = 1 + dp(i + 1, j + 1);
        } else {
            mem[i][j] = Math.max(dp(i + 1, j), dp(i, j + 1));
        }

        return mem[i][j];
    }

    /**
     *
     * Time : O(m * n)
     * Space : O(m * n)
     * Runtime : 30 ms (41.74%)
     * Memory : 50.3 MB (52.40%)
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                dp[i + 1][j + 1]
                        = text1.charAt(i) == text2.charAt(j) ? 1 + dp[i][j] : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        return dp[len1][len2];
    }

    /**
     *
     * Time : O(m * n)
     * Space : O(n)
     * Runtime : 7 ms (99.47%)
     * Memory : 41 MB (94.53%)
     */
    public int longestCommonSubsequence3(String text1, String text2) {
        if (text1.length() < text2.length())
            return longestCommonSubsequence(text2, text1);

        char[] t1=text1.toCharArray();
        char[] t2=text2.toCharArray();

        int[] curr = new int[text2.length() + 1];
        int[] prev = new int[text2.length() + 1];

        for (int i = text1.length() - 1; i >= 0; i--) {
            for (int j = text2.length() - 1; j >= 0; j--) {
                if (t1[i] == t2[j]) {
                    curr[j] = prev[j + 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j + 1]);
                }
            }
            int[] tmp = prev;
            prev = curr;
            curr = tmp;
        }
        return prev[0];
    }
}
