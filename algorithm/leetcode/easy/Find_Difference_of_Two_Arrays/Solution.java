package algorithm.leetcode.easy.Find_Difference_of_Two_Arrays;

import java.util.*;

public class Solution {
    /**
     * nums1과 nums2에서 중복된 숫자를 제거하고 특정 숫자의 포함 여부를 확인하기 위해 HashSet을 사용한다.
     *
     * nums 배열 원소 크기의 범위가 [-1000, 1000]이므로, 중복 확인을 위해 크기가 2001인 boolean 배열을 사용하는 것도 가능하긴 하다.
     * 그러나, 주어진 배열의 크기가 훨씬 작아도 boolean 배열의 크기를 줄일 수 없으므로 메모리 활용 측면에서 바람직하지 않다.
     *
     * Time : O(m + n) (m -> nums1.length(), n -> nums2.length())
     * Space : O(m + n)
     * Runtime : 0 ms (100%)
     * Memory : 40.4 MB (89.85%)
     */
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();

        // 두 HashSet에 중복되지 않은 숫자들을 담는다.
        for (int n : nums1) {
            set1.add(n);
        }

        for (int n : nums2) {
            set2.add(n);
        }

        // 어떤 배열의 원소가 다른 배열에 존재하지 않는다면 정답 배열에 포함한다
        for (int n : set1) {
            if (!set2.contains(n)) {
                ans1.add(n);
            }
        }

        for (int n : set2) {
            if (!set1.contains(n)) {
                ans2.add(n);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>(ans1));
        ans.add(new ArrayList<>(ans2));

        return ans;
    }
}
