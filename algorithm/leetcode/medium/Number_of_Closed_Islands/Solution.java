package algorithm.leetcode.medium.Number_of_Closed_Islands;

public class Solution {
    /**
     * 문제 링크 : https://leetcode.com/problems/number-of-closed-islands/
     *
     * dfs 함수의 형태가 낯설어서 조금 헤맸다.
     * 초반에 return 값을 달리 하는 두 개의 조건문을 배치하고
     * 후반에 재귀 호출한 함수의 return 값을 묶어주는 연산을 한다.
     *
     * 이 풀이에서는 ans 값을 세는 과정을 간결하게 나타내기 위해 dfs의 리턴 타입을 int로 설정했지만
     * 리턴 타입을 boolean으로 설정했을 때 수행시간이 1 ms로 더 짧게 나오긴 한다.
     *
     * Time : O(n*m)
     * Space : O(n*m) (재귀 호출할 때마다 콜스택에 함수의 데이터 저장)
     * Runtime : 2 ms (82.36%)
     * Memory : 43.5 MB (16.44%)
     */
    private int rows, cols;
    public int closedIsland(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) continue;
                ans += dfs(i, j, grid);
            }
        }

        return ans;
    }

    private int dfs(int r, int c, int[][] grid) {
        if (r < 0 || c < 0 || r == rows || c == cols)
            return 0;

        if (grid[r][c] == 1)
            return 1;

        grid[r][c] = 1;

        // 인접한 네 칸 중 하나라도 0이면 closed island가 아니므로 0을 반환
        return (
            dfs(r+1, c, grid)+
            dfs(r-1, c, grid)+
            dfs(r, c+1, grid)+
            dfs(r, c-1, grid)
        ) / 4;
    }
}
