package algorithm.leetcode.medium.Spiral_Matrix_II;

public class Solution {
    /**
     * n=3인 경우, 3*3 배열을 나선형으로 채우는 경우를 살펴보자.
     * 이때, top, bottom, left, right은 행과 열의 탐색 범위를 가리킨다.
     *
     *       left right
     *    top [a b c]
     *        [d e f]
     * bottom [g h i]
     *
     * 첫 번째 행(a, b, c)을 채우고 나면, 첫 번째 행은 이후의 탐색 범위에 해당하지 않으므로 top을 1칸 아래로 옮길 수 있다.
     * 마찬가지로, 이후 f, i의 값을 채우며 마지막 열을 완성하면 이 역시 탐색 범위에서 벗어나므로 right를 1칸 왼쪽으로 옮길 수 있다.
     * 이런 식으로 top -> right -> bottom -> left의 값을 1씩 변경하고 나면, 내부의 작은 정사각형 배열을 채우는 sub problem이 시작되며,
     * 이에 대해서도 동일한 연산을 이어가면 된다.
     *
     * Time : O(n^2)
     * Space : O(1)
     * Runtime : 0 ms (100%)
     * Memory : 40.4 MB (89.85%)
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int num = 1;

        // 정사각형 배열에서 배열의 바깥쪽 행과 열을 채우고 나면 left와 right, top과 bottom의 간격은 동일할 것이므로
        // left와 right에 대해서만 종료조건을 확인한다
        while (left <= right) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                res[i][right] = num++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                res[bottom][i] = num++;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                res[i][left] = num++;
            }
            left++;
        }

        return res;
    }
}
