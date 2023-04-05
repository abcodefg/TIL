package algorithm.leetcode.medium.Optimal_Partition_of_String;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * [Set을 활용한 풀이]
     *
     * 나는 Set을 활용해 풀어 보았다.
     * 주어진 문자열의 각 자리를 순회하며 한 partition을 구성하는 문자들을 set에 담는다.
     * 어떤 문자가 이미 set에 담겨있다면 partition을 추가해야 하므로 ans를 1 증가시키고 set을 비운다.
     *
     * Time : O(n) (이 경우, HashSet.contains()의 time complexity는 O(1))
     * Space : O(1) (Set의 크기가
     * Runtime : 36 ms (51.93%)
     * Memory : 43.6 MB (35.14%)
     */
    public int solutionUsingSet(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 1;
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                ans++;
                set.clear();
            }
            set.add(c);
        }

        return ans;
    }

    /**
     * [배열을 활용한 풀이]
     *
     * int 배열 lastPos에는 각 알파벳이 마지막으로 나타났던 partition의 순서를,
     * lastEnd에는 마지막 partition의 순서를 기록한다.
     *
     * Time : O(n)
     * Space : O(1) (배열의 길이가 알파벳 개수인 26이므로 상수)
     * Runtime : 9 ms (92.55%)
     * Memory : 42.6 MB (92.65%)
     */
    public int solutionUsingArray(String s) {
        int[] lastPos = new int[26];
        // Set을 활용한 풀이와는 달리 첫 partition부터 ans가 1 증가하므로 ans의 초기값은 0으로 설정
        //  (int 배열의 초기값 0 >= lastEnd이므로)
        int ans = 0;
        int lastEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if (lastPos[val] >= lastEnd) {
                ans++;
                lastEnd = i + 1;
            }
            lastPos[val] = i + 1;
        }

        return ans;
    }

    /**
     * [비트 마스크를 활용한 풀이]
     *
     * 기본적인 접근법은 위의 두 풀이와 동일하나 한 partition에 등장한 문자열을 기록하고 확인할 때 비트마스크를 활용한다.
     * 따라서 메모리 사용 측면에서 우수하다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 14 ms (Beats 98.98%)
     * Memory : 10.2 MB (Beats 98.68%)
     */
    public int solutionUsingBitMasking(String s) {
        int ans = 1, flag = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if ((flag & (1 << val)) != 0) {
                flag = 0;
                ans++;
            }
            flag = flag | (1 << val);
        }

        return ans;
    }
}
