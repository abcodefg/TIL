package algorithm.leetcode.medium.Number_of_Enclaves;

public class Solution {
    /**
     * 주어진 배열에서 모든 1의 개수(=land)를 세고,
     * 가장자리에 있는 1에서 dfs를 시작해 가장자리와 연결되어 있는 land의 개수(=borderLand)를 센 뒤,
     * land - borderLand를 반환하는 방식으로 풀었다.
     *
     * Time : O(m * n)
     * Space : O(m * n)
     * Runtime : 9 ms (Beats 77.98%)
     * Memory : 56 MB (Beats 35.41%)
     */
    private int rows, cols;
    private boolean[][] visited;
    public int solution1(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];

        int land = 0, borderLand = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                land += grid[i][j];
                if ((i == 0 || j == 0 || i == rows - 1 || j == cols - 1) && grid[i][j] == 1) {
                    borderLand += dfs(i, j, grid);
                }
            }
        }

        return land - borderLand;
    }

    private int dfs(int r, int c, int[][] grid) {
        if (r < 0 || c < 0 || r == rows || c == cols || grid[r][c] == 0 || visited[r][c]) {
            return 0;
        }

        visited[r][c] = true;

        return 1 + dfs(r - 1, c, grid)
                + dfs(r + 1, c, grid)
                + dfs(r, c - 1, grid)
                + dfs(r, c + 1, grid);
    }

    /**
     * 이전 풀이에서는
     *  1. 주어진 배열에 존재하는 1의 개수를 세고,
     *  2. 가장자리에 인접한 1과 연결된 1을 센다.
     *
     * 그런데 다른 사람의 풀이를 살펴보니, 순서만 바꾸면 visited 배열을 통해 방문 여부를 체크할 필요가 없었다.
     * 따라서 solution2가 1보다 공간 복잡도 측면에서 개선된 풀이다.
     *
     * Time : O(m * n)
     * Space : O(1)
     * Runtime : 9 ms (Beats 77.98%)
     * Memory : 54.7 MB (Beats 60.57%)
     */
    public int solution2(int[][] grid) {
        int result = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1)
                    dfs(grid, i, j);
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1)
                    result++;
            }
        }

        return result;
    }

    public void dfs(int grid[][], int i, int j) {
        if(i >= 0 && i <= grid.length - 1 && j >= 0 && j <= grid[i].length - 1 && grid[i][j] == 1) {
            grid[i][j] = 0;
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }
}
