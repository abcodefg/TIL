package algorithm.leetcode.medium.Redundant_Connection;

import java.util.*;

public class Solution {
    /**
     * [DFS 풀이]
     *
     * Time : O(n^2)
     * Space : O(n)
     * Runtime : 4 ms (Beats 21.11%)
     * Memory : 43.7 MB (Beats 23.91%)
     */
    public int[] findRedundantConnection_DFS(int[][] edges) {
        int[] ans = null;
        int n = edges.length;
        List<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();

        for(int[] edge : edges){
            int u = edge[0], v = edge[1];
            if(alreadyConnected(u, v, 0, adj)){
                ans = edge;
            }else{
                adj[u].add(v);
                adj[v].add(u);
            }
        }
        return ans;
    }

    private boolean alreadyConnected(int u, int v, int pre, List<Integer>[] adj){
        if(u == v)
            return true;
        for(int w : adj[u]){
            if(w == pre) continue;
            boolean ret = alreadyConnected(w, v, u, adj);
            if(ret) return true;
        }
        return false;
    }

    /**
     *  [Union Find 풀이]
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 1 ms (Beats 67%)
     * Memory : 43.7 MB (Beats 23.91%)
     */
    private int[] parent;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        int[] ans = null;
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            int A = find(a), B = find(b);

            if (A == B) {
                ans = edge;
                break; //
            } else {
                union(A, B);
            }
        }

        return ans;
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private void union(int a, int b) {
        parent[b] = a;
    }
}
