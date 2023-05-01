package algorithm.leetcode.medium.Valid_Sudoku;

import java.util.HashSet;

public class Solution {
    /**
     *  [boolean 배열을 활용한 풀이]
     *
     *  Time : O(9^2)
     *  Space : O(9^2)
     *  Runtime : 1 ms (100%)
     *  Memory : 42.8 MB (56.41%)
     */
    public boolean isValidSudoku1(char[][] board) {

        // 행, 열, 작은 정사각형의 중복을 체크할 배열
        // r[0][0] = true라면 a번 행에 숫자 1('1' - '1' = 0)이 이미 있다는 것을 의미한다.
        boolean[][] r = new boolean[9][9];
        boolean[][] c = new boolean[9][9];
        boolean[][] sub = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int n = board[i][j] - '1';
                // 현재 좌표를 각각 9개의 작은 큐브를 나타내는 0~9의 숫자로 치환한다
                int k = 3 * (i / 3) + j / 3;

                if (r[i][n] || c[j][n] || sub[k][n]) return false;
                r[i][n] = true;
                c[j][n] = true;
                sub[k][n] = true;
            }
        }

        return true;
    }

    /**
     *  [HashSet을 활용한 풀이]
     *
     *  Time : O(9^2)
     *  Space : O(9)
     *  Runtime : 3 ms (70.49%)
     *  Memory : 45.4 MB (84.64%)
     */
    public boolean isValidSudoku2(char[][] board) {

        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cube = new HashSet<>();

            for (int j = 0; j < 9; j++) {
                // 숫자가 이미 HashSet에 존재한다면(rows.add(board[i][j] = false)
                // => false를 반환
                if(board[i][j]!='.' && !rows.add(board[i][j]))
                    return false;
                // TODO Check (1) : 행렬 좌표 바꿔서 기록
                if(board[j][i]!='.' && !cols.add(board[j][i]))
                    return false;

                // TODO Check (2) : matrix traversal에 유용한 좌표 표기법
                // '/' -> vertical traversal. (이 경우, 3마다 증가)
                // '%' -> horizontal traversal (이 경우, 1마다 증가)
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                    return false;
            }
        }

        return true;
    }
}
