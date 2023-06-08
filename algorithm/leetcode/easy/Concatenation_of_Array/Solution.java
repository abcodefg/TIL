package algorithm.leetcode.easy.Concatenation_of_Array;

public class Solution {
    /**
     * 주어진 배열을 그대로 오른쪽에 덧붙인 배열을 반환하는 문제이다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 0 ms (Beats 100%)
     * Memory : 44.3 MB (Beats 28.59%)
     */
    public int[] getConcatenation(int[] nums) {
        int[] ans = new int[2 * nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = ans[i + nums.length] = nums[i];
        }

        return ans;
    }
}
