package algorithm.leetcode.easy.Merge_Strings_Alternately;

public class Solution {
    /**
     * [Two Pointer]
     * word1과 word2의 각 문자를 가리키는 포인터 두 개를 사용하는 풀이이다.
     *
     * 다른 투 포인터 풀이를 보니 while 문 안에 if 문을 두어
     *  word1 혹은 word2에서 아직 남은 문자가 있을 경우 계속 반복문을 순회하게 하는 간결한 형태로 구성하기도 했는데,
     *  그보디는 남은 부분은 substring()으로 잘라 한 번에 붙이는 것이 실행시간 측면에서 더 효율적이다.
     *
     * Time : O(n + m)
     * Space : O(n + m)
     * Runtime : 0 ms (Beats 100%)
     * Memory : 40.3 MB (Beats 98.62%)
     */
    public String mergeAlternately1(String word1, String word2) {
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < word1.length() && j < word2.length()) {
            sb.append(word1.charAt(i++)).append(word2.charAt(j++));
        }

        if (i < word1.length()) {
            sb.append(word1.substring(i));
        }
        if (j < word2.length()) {
            sb.append(word2.substring(j));
        }

        return sb.toString();
    }

    /**
     * [One Pointer]
     * 포인터를 하나만 사용한 풀이이다.
     *
     * Time : O(n) (n = word1과 word2 중 더 긴 것의 길이)
     * Space : O(n + m)
     * Runtime : 1 ms (Beats 81.7%)
     * Memory : 40.6 MB (Beats 88.88%)
     */
    public String mergeAlternately2(String word1, String word2) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < word1.length() || i < word2.length(); ++i) {
            if (i < word1.length())
                res.append(word1.charAt(i));
            if (i < word2.length())
                res.append(word2.charAt(i));
        }
        return res.toString();
    }
}
