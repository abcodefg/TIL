package algorithm.leetcode.medium.Maximum_Width_of_Binary_Tree;
import java.util.*;

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
     * [BFS 풀이]
     * 이진트리의 각 노드에 번호를 매겨서 각 level의 width를 구할 수 있다.
     * 이 때, 두 가지 방식으로 번호를 매길 수 있다.
     *  1) 루트 노드의 번호가 0 -> 부모 노드가 n일 때 왼쪽 자식 노드 2n+1, 오른쪽 자식 노드 2n+2
     *  2) 루트 노드의 번호가 1 -> 부모 노드가 n일 때 왼쪽 자식 노드 2n, 오른쪽 자식 노드 2n+1
     *
     * 첫 번째 방식으로 각 노드에 번호를 매긴 다음과 같은 이진트리가 있다고 하자.
     *      0
     *    1   2
     *   3 4   6
     *  위에서부터 두 번째 level의 width는 2 - 1 + 1 = 2이고
     *  세 번째 level의 width는 6 - 3 + 1 = 4이다.
     *
     * Time : O(n) (n = 트리의 모든 노드의 수)
     * Space : O(n / 2) = O(n)
     * Runtime : 1 ms (100%)
     * Memory : 42.5 MB (28.18%)
     */
    static class Pair {
        TreeNode node;
        int idx;
        Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }
    }
    public static int widthOfBinaryTree1(TreeNode root) {
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root, 0));
        int max = 0;
        while (!q.isEmpty()) {
            int n = q.size();
            int minIdx = q.peek().idx;
            int first = 0, last = 0;
            for (int i = 0; i < n; i++) {
                int curIdx = q.peek().idx - minIdx; // 어떤 level의 가장 왼쪽 노드의 인덱스가 0이 되도록 한다.
                TreeNode curNode = q.poll().node;

                if (i == 0) first = curIdx; // 해당 level의 첫 노드와 마지막 노드의 인덱스를 기록한다.
                if (i == n - 1) last = curIdx;

                if (curNode.left != null) q.add(new Pair(curNode.left, 2 * curIdx + 1));    // 각 노드들의 자식 노드를 큐에 추가한다.
                if (curNode.right != null) q.add(new Pair(curNode.right, 2 * curIdx + 2));
            }

            max = Math.max(max, last - first + 1);
        }

        return max;
    }

    /**
     * [DFS 풀이]
     * 각 level마다 가장 왼쪽에 있는 노드들의 인덱스를 lefts에 기록하고
     * 이를 바탕으로 각 노드까지의 width를 구하는 DFS 풀이이다.
     *
     * Time : O(n) (n = 트리의 모든 노드의 수)
     * Space : O(h) (h = 트리의 높이)
     * Runtime : 1 ms (100%)
     * Memory : 41.9 MB (90.5%)
     */
    public int widthOfBinaryTree2(TreeNode root) {
        List<Integer> lefts = new ArrayList<Integer>(); // 각 level의 맨 왼쪽에 있는 노드
        return dfs(root, 1, 0, lefts);
    }

    private int dfs(TreeNode n, int id, int d, List<Integer> lefts) { // d = depth = level
        if (n == null) return 0;
        if (d >= lefts.size()) lefts.add(id);   // add left most node
        return Math.max(id + 1 - lefts.get(d), Math.max(dfs(n.left, id*2, d+1, lefts), dfs(n.right, id*2+1, d+1, lefts)));
    }
}
