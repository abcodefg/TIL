package algorithm.leetcode.medium.Group_Anagrams;

import java.util.*;

public class Solution {
    /**
     * 문자열을 구성하는 각 알파벳의 개수를 key로 삼아 anagram끼리 묶어서 map에 저장한다.
     *
     * Time : O(m * n) (m = strs.length, n = strs[i].length의 최대값)
     * Space : O(m * n)
     * Runtime : 22 ms (19.64%)
     * Memory : 47.2 MB (9.98%)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            // str를 구성하는 문자의 개수를 기록하고 key로 활용
            int[] let = new int[26];
            for (char c : str.toCharArray()) {
                let[c - 'a']++;
            }
            String key = String.valueOf(let);

            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());

            map.get(key).add(str);

        }

        return new ArrayList<>(map.values());
    }
}
