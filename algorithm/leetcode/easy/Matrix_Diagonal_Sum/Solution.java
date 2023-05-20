package algorithm.leetcode.easy.Matrix_Diagonal_Sum;

public class Solution {
    /**
     * primary diagonal은 row와 column의 인덱스가 같고,
     * secondary diagonal은 row와 column의 인덱스의 합이 'matrix의 길이 - 1'과 같다.
     *
     * 이 점을 활용하여 diagonal에 속하는 인덱스의 값을 결과값에 더한다.
     * 단, matrix의 길이가 홀수인 경우 가운데 값이 두 번 더해지므로 마지막에 가운데 값을 한 번 빼준다.
     *
     * Time : O(n) (n = mat.length)
     * Space : O(1)
     * Runtime : 0 ms (100%)
     * Memory : 44.3 MB (5.99%)
     */
    public int diagonalSum(int[][] mat) {
        int res = 0;
        int len = mat.length;
        for (int i = 0; i < len; i++) {
            // 결과값에 primary, secondary diagonal에 속하는 요소의 값을 더한다
            res += mat[i][i] + mat[i][len - i - 1];
        }

        // matrix의 길이가 홀수인 경우 두 번 더해진 가운데 값을 빼준다
        if (len % 2 == 1)
            res -= mat[len / 2][len / 2];

        return res;
    }
}
