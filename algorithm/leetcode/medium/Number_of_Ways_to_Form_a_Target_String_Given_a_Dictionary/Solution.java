package algorithm.leetcode.medium.Number_of_Ways_to_Form_a_Target_String_Given_a_Dictionary;

public class Solution {
    private int wlen, tlen;
    private long mod;
    private char[] t;
    private int[][] chars;
    private Long[][] dp;
    public int numWays(String[] words, String target) {
        wlen = words[0].length();
        tlen = target.length();
        mod = (long) Math.pow(10, 9) + 7;
        t = target.toCharArray();
        chars = new int[wlen][26];
        dp = new Long[tlen][wlen];

        for (String word : words) {
            for (int i = 0; i < wlen; i++) {
                chars[i][word.charAt(i) - 'a']++;
            }
        }

        return (int) recur(0, 0);
    }

    private long recur(int i, int k) {
        if (i == tlen) return 1;
        if (k == wlen) return 0;
        if (dp[i][k] != null) return dp[i][k];


        dp[i][k] = recur(i, k + 1) + chars[k][t[i] - 'a'] * recur(i + 1, k + 1);

        return dp[i][k] % mod;
    }
}
