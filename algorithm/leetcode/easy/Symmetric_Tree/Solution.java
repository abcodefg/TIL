package algorithm.leetcode.easy.Symmetric_Tree;

public class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Time : O(n)
     * Space : O(h) (h는 Tree의 높이 -> DFS)
     * Runtime : 0 ms (Beats 100%)
     * Memory : 40.6 MB (Beats 81.55%)
     */
    public boolean isSymmetric(TreeNode root) {
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode a, TreeNode b) {
        if (a == null || b == null) // 어느 한 쪽이 null인 경우 대칭일 때만 참
            return a == null && b == null;  // a == b 라고 써도 된다

        return a.val == b.val && dfs(a.left, b.right) && dfs(a.right, b.left);
    }
}
