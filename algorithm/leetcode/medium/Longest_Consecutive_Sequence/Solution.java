package algorithm.leetcode.medium.Longest_Consecutive_Sequence;

import java.util.*;

public class Solution {
    /**
     * [HashSet을 활용한 풀이]
     *  HashSet에 nums의 숫자들을 삽입하면,
     *  HashSet.contains()를 통해 평균 O(1)의 시간 복잡도로 어떤 숫자보다 1 크거나 작은 값이 존재하는지 확인할 수 있다.
     *
     *  Time : O(n)
     *  Space : O(n)
     *  Runtime : 25 ms (73.54%)
     *  Memory : 61.2 MB (13.37%)
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 0;
        for (int num : set) {
            if (set.contains(num - 1)) continue;
            // 어떤 숫자보다 1 작은 값이 없다면 ( = consecutive sequence의 시작점이라면)

            // sequence의 길이를 재고 최대값을 갱신한다
            int count = 1;
            while (set.contains(num + 1)) {
                num = num + 1;
                count++;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
