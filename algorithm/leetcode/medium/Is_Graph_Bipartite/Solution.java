package algorithm.leetcode.medium.Is_Graph_Bipartite;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    private int[] odd;
    public boolean isBipartite(int[][] graph) {
        odd = new int[graph.length];

        for (int i = 0; i < graph.length; i++) {
            if (!bfs(i, graph)) return false;
        }

        return true;
    }

    private boolean bfs(int n, int[][] graph) {
        if (odd[n] != 0) return true;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        odd[n] = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : graph[cur]) {
                if (odd[next] != 0 && odd[cur] == odd[next])
                    return false;
                else if (odd[cur] == 0) {
                    q.add(next);
                    odd[next] = -1 * odd[cur];
                }
            }
        }

        return true;
    }
}
