package algorithm.leetcode.medium.Permutation_in_String;

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1Count[s1.charAt(i) - 'a']++;
            s1Count[s2.charAt(i) - 'a']++;
        }

        int match = 0;
        for (int i = 0; i < 26; i++) {
            if (s1Count[i] == s2Count[i]) {
                match++;
            }
        }

        int l = 0;
        for (int r = s1.length(); r < s2.length(); l++, r++) {
            if (match == 26) return true;

            int prevC = s2.charAt(l) - 'a';
            int newC = s2.charAt(r) - 'a';
            s2Count[prevC]--;
            match = s1Count[prevC] == s2Count[prevC] ? match + 1 : match - 1;
            s2Count[newC]++;
            match = s1Count[newC] == s2Count[newC] ? match + 1 : match - 1;
        }

        return false;
    }
}
