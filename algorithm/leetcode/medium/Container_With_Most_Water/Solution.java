package algorithm.leetcode.medium.Container_With_Most_Water;

public class Solution {
    /**
     *  [Two Pointer 풀이]
     *  배열의 양 끝에 두 개의 포인터를 두고 이들을 옮겨가며 포인터가 가리키는 line들로 이루어진 container에 담을 수 있는 물의 양을 구한다.
     *  이 때 두 가지를 고려해야 한다.
     *      1. 물의 양을 구할 때, 높이는 두 line의 높이 중 더 짧은 것이다.
     *      2. 포인터를 옮길 때, 물의 양이 확실하게 줄어드는 방향은 피해야 한다.
     *
     *  int[] height = [1,8,6,2,5,4,8,3,7]인 경우를 살펴보자.
     *  포인터 i = 0, j = 8인 경우 container에 물이 차오를 수 있는 높이는 1과 7 중 더 작은 1이고 너비는 8이므로, 물의 양은 1 * 8 = 8이다.
     *  그런데 물의 높이가 i의 높이인 1로 제한되어 있는 상황에서 포인터 j의 위치를 옮겨봤자 높이는 늘지 않고 너비는 줄어들 것이므로 물의 양은 8보다 작을 것이다.
     *  따라서, 높이가 작은 쪽인 포인터(이 경우에 i)를 가운데 방향으로 한 칸씩 옮겨야 불필요하게 물의 양이 확실하게 줄어드는 경우를 탐색하지 않을 수 있다.
     *
     *  두 포인터가 가리키는 line의 높이가 동일한 경우엔 어느 쪽을 옮겨도 상관없다.
     *  둘 사이에 현재 높이보다 높은 line이 있다면 포인터가 멈출 것이기 때문이다.
     *
     *  Time : O(n)
     *  Space : O(1)
     *  Runtime : 4 ms (81.8%)
     *  Memory : 52.7 MB (34.84%)
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            int left = height[i], right = height[j];
            // 물의 양을 구하고 최댓값을 갱신한다.
            max = Math.max(max, (j - i) * Math.min(left, right));

            if (left <= right) {
                i++;
            } else {
                j--;
            }
        }

        return max;
    }
}
