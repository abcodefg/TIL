package algorithm.leetcode.medium.Find_Peak_Element;

public class Solution {
    /**
     * 좌우에 인접한 두 요소보다 값이 큰 peak element를 찾는 문제이다.
     * 주목해야 할 두 가지 조건이 있다.
     *  1) O(log n)의 시간 복잡도를 갖는 알고리즘이어야 한다.
     *     시간복잡도가 O(log n)인 배열 탐색 알고리즘으로는 binary search가 있다.
     *     그런데 정렬된 배열에서 특정 값을 찾는 문제가 아니기 때문에 이를 변형해서 적용해야 한다.
     *     즉, 탐색 범위를 반씩 좁히되, 어느 방향으로 좁힐지의 기준을 정해야 한다.
     *
     *  2) 연속한 두 요소는 반드시 다른 값을 갖는다. (nums[i] != nums[i + 1])
     *     특정 요소 nums[i]를 기준으로 좌우에 인접한 두 요소는 nums[i]보다 크거나 작을 것이다.
     *     만약 nums[i+1]가 nums[i]보다 크다면 i보다 오른쪽 요소들 중에는 반드시 peak element가 있다.
     *      nums[i] < nums[i+1] > nums[i+2]인 경우, nums[i+1]이 peak element이며,
     *      nums[i] < nums[i+1] < nums[i+2]인 경우, j >= i + 1인 nums[j]가 peak element이기 때문이다.
     *      (설령 배열의 끝까지 점증한다고 하더라도 배열 밖의 숫자는 인접한 배열의 원소보다 작다고 간주된다.)
     *
     * 따라서, 중간 지점의 원소보다 큰 neighbor element가 있는 쪽으로 탐색 범위를 좁히는 binary search를 활용한다.
     *
     * Time : O(log n)
     * Space : O(1)
     * Runtime : 0 ms (Beats 100%)
     * Memory : 42.5 MB (Beats 20.45%)
     */
    public static int solution1(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] < nums[mid + 1])
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }
}
