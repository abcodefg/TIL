package algorithm.leetcode.medium.Remove_Duplicates_From_Sorted_Array_Two;

public class Solution {
    /**
     * [Two Pointer 풀이]
     *
     * Time : O(2 * n) = O(n)
     * Space : O(1)
     * Runtime : 1 ms (23.72%)
     * Memory : 42.7 MB (62.34%)
     */
    public int removeDuplicates1(int[] nums) {
        int l = 0, r = 0;

        while (r < nums.length) {
            int cnt = 1;
            while (r + 1 < nums.length && nums[r] == nums[r + 1]) {
                r++;
                cnt++;
            }

            for (int i = 0; i < Math.min(2, cnt); i++) {
                nums[l] = nums[r];
                l++;
            }

            r++;
        }

        return l;
    }

    /**
     * [One Pointer 풀이]
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 0 ms (100%)
     * Memory : 43 MB (37.75%)
     */
    public int removeDuplicates2(int[] nums) {
        int index = 1;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]){
                count++;
            }
            else{
                count = 0;
            }

            if(count <= 1){
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
