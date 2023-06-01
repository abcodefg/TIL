package algorithm.leetcode.easy.Sign_of_the_Product_of_an_Array;

public class Solution {
    public int arraySign(int[] nums) {
        int ans = 1;
        for (int num : nums) {
            if (num == 0) return 0;
            if (num < 0)
                ans = -ans;
        }

        return ans;
    }
}
