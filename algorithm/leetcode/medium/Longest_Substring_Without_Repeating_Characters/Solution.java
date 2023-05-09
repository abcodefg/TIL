package algorithm.leetcode.medium.Longest_Substring_Without_Repeating_Characters;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l = 0, r = 0, max = 0;
        char[] c = s.toCharArray();
        boolean[] check = new boolean[26];

        while (r < s.length()) {
            while (check[c[r] - 'a']) {
                check[c[l] - 'a'] = false;
                l++;
            }
            check[c[r] - 'a'] = true;

            max = Math.max(max, r - l + 1);
        }

        return max;
    }
}
