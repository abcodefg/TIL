package algorithm.leetcode.hard.Find_the_Longest_Valid_Obstacle_Course_at_Each_Position;

import java.util.Arrays;

public class Solution {
    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int len = obstacles.length;
        int[] ans = new int[len];
        int[] dp = new int[len + 1];
        Arrays.fill(dp, 10^8);

        for (int i = 0; i < len; ++i) {
            int idx = binary(obstacles[i], dp);
            dp[idx - 1] = obstacles[i];
            ans[i] = idx;
        }

        return ans;
    }

    private int binary(int n, int[] minIdx) {
        int lo = 1;
        int hi = minIdx.length - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (minIdx[mid] > n) hi = mid;
            else lo = mid + 1;
        }

        return hi;
    }
}
