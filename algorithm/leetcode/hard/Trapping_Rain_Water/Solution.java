package algorithm.leetcode.hard.Trapping_Rain_Water;

public class Solution {
    /**
     * [DP 풀이]
     * elevation map이 다음과 같이 주어진 경우를 살펴보자.
     *                         *
     *                 *       *
     *             *   * *   * *
     *           ---------------
     * index  :  0 1 2 3 4 5 6 7
     *
     * 양 끝(0번과 7번) 자리에는 빗물이 고이지 않고 밖으로 흘러내린다.
     *
     * 2번 자리는 높이 0부터 바로 왼쪽인 1번 자리의 높이 1까지만 빗물이 차오를 수 있으므로 고인 빗물의 양은 1-0 = 1이다.
     * 4,5,6번 자리는 각각 높이 1, 0, 1부터, 3번과 7번 중 높이가 낮은 3번 자리의 높이 2까지만 빗물이 차오를 수 있으므로 고인 빗물의 양은 2에서 각 자리의 높이를 뺀 값을 합한 4이다.
     *
     * 앞의 경우와 달리 3번 자리는 3번을 기준으로 왼쪽, 오른쪽에서 가장 높은 1번, 7번 중 높이가 낮은 1번의 1보다 3번의 높이 2가 크므로 1-2 = -1인데, 빗물은 음의 값만큼 고일 수 없으므로 0보다 작은 값은 무시한다.
     *
     * 이런 방식으로 구한 각 자리에 고이는 빗물의 양의 총합을 반환한다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 1 ms (Beats 97.68%)
     * Memory : 44.1 MB (Beats 33.8%)
     */
    public int trap_DP(int[] height) {
        int len = height.length;

        // elevation map의 각 지점을 기준으로 왼쪽과 오른쪽 높이의 최대값을 기록한다
        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        int max = height[0];
        for (int i = 1; i <= len - 1; ++i) {
            max = Math.max(max, height[i - 1]);
            maxLeft[i] = max;
        }

        max = height[len - 1];
        for (int i = len - 2; i >= 0; --i) {
            max = Math.max(max, height[i + 1]);
            maxRight[i] = max;
        }

        // 각 자리에 고이는 빗물의 양을 구한다
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans += Math.max(0, Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }

        return ans;
    }

    /**
     * [Two Pointer 풀이]
     * 위의 풀이와 동일한 논리를 Two Pointer를 활용해 구현한 풀이로,
     * 추가적인 배열 없이 변수만으로 구현할 수 있으므로 공간복잡도 측면에서 더 효율적이다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 1 ms (Beats 97.68%)
     * Memory : 44.8 MB (Beats 7.65%)
     */
    public int trap_TwoPointer(int[] height) {
        int l = 0, r = height.length - 1;
        int maxLeft = height[l], maxRight = height[r];
        int ans = 0;

        while (l < r) {
            if (height[l] <= height[r]) {
                l++;
                ans += Math.max(0, maxLeft - height[l]);
                maxLeft = Math.max(maxLeft, height[l]);
            } else {
                r--;
                ans += Math.max(0, maxRight - height[r]);
                maxRight = Math.max(maxRight, height[r]);
            }
        }

        return ans;
    }
}
