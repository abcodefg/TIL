package algorithm.leetcode.medium.Three_Sum;

import java.util.*;

public class Solution {
    /**
     *  Time : O(n^2) (nested loop)
     *  Space : O(n)
     *  Runtime : 32 ms (97.2%)
     *  Memory : 50.6 MB (24.55%)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        int i = 0;

        while (i < nums.length - 2 && nums[i] <= 0) {

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);

                    ans.add(triplet);

                    k--;

                    int current = nums[j];
                    j++;

                    while (j < k && current == nums[j])
                        j++;
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }

            int prev = nums[i];
            while (i < nums.length && prev == nums[i]) {
                i++;
            }
        }

        return ans;
    }
}
