package algorithm.leetcode.medium.Spiral_Matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * matrix와 다음과 같이 주어지는 경우를 살펴보자.
     * {{1, 2, 3, 4},
     *  {5, 6, 7, 8},
     *  (9,10,11,12}}
     * 1,2,3,4,8,12,11,10,9,5의 이차원 배열의 가장자리의 숫자들을 순회하고 나면,
     * 내부의 작은 배열
     * {{6, 7},
     *  {10,11}}
     *  을 순회하는 sub problem으로 이어진다.
     *  좌, 우, 상, 하의 인덱스를 설정하고
     *  한 줄의 탐색이 끝날 때마다 좌, 상의 값을 1씩 늘리고, 우, 하의 값을 1씩 줄인다.
     *
     * 내부의
     * Time : O(m * n)
     * Space : O(1) (반환하는 List 제외)
     * Runtime : 0 ms (100%)
     * Memory : 40.4 MB (89.85%)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = matrix[0].length;
        int top = 0, bottom = matrix.length;

        while (left < right && top < bottom) {
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            top++;

            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right - 1]);
            }
            right--;

            // 주의: 중간에 탐색 종료 조건을 넣어주어야 한다
            if (!(left < right && top < bottom)) break;

            for (int i = right - 1; i >= left; i--) {
                res.add(matrix[bottom - 1][i]);
            }
            bottom--;

            for (int i = bottom - 1; i >= top; i--) {
                res.add(matrix[i][left]);
            }

            left++;
        }

        return res;
    }
}
