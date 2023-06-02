package algorithm.leetcode.easy.Sqrt_x;

public class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x;
        int ans = 0;

        while (l <= r) {
            int m = l + ((r - 1) / 2);
            if (m * m > x) {
                r = m - 1;
            } else if (m * m < x) {
                l = m + 1;
                ans = m;
            } else {
                return m;
            }
        }

        return ans;
    }
}
