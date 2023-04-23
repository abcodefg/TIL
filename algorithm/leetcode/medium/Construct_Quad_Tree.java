package algorithm.leetcode.medium;

public class Construct_Quad_Tree {
    /**
     * Time : O(n^2 * log n)
     * Space : O(h) (h는 Tree의 높이 -> DFS)
     * Runtime : 0 ms (Beats 100%)
     * Memory : 42.6 MB (Beats 91%)
     */
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length);
    }

    private Node dfs(int[][] grid, int r, int c, int n) {
        int num = grid[r][c];
        for (int i = r; i < r + n; i++) {
            for (int j = c; j < c + n; j++) {
                if (grid[i][j] != num) {
                    Node node = new Node(true, false);
                    node.topLeft = dfs(grid, r, c, n/2);
                    node.topRight = dfs(grid, r, c + n/2, n/2);
                    node.bottomLeft = dfs(grid, r + n/2, c, n/2);
                    node.bottomRight = dfs(grid, r + n/2, c + n/2, n/2);
                    return node;
                }
            }
        }

        return new Node(grid[r][c] == 1, true);
    }

    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
