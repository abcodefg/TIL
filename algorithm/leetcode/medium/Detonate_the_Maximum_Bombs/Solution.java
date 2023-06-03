package algorithm.leetcode.medium.Detonate_the_Maximum_Bombs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int maximumDetonation(int[][] bombs) {
        int len = bombs.length;

        List<Integer>[] adj = new ArrayList[len];
        for (int i = 0; i < len - 1; i++) {
            if (adj[i] == null) {
                adj[i] = new ArrayList<>();
            }

            for (int j = i + 1; j < len; j++) {

                if (adj[j] == null) {
                    adj[j] = new ArrayList<>();
                }
                double dis = Math.sqrt(Math.pow(bombs[i][0] - bombs[j][0], 2) + Math.pow(bombs[i][1] - bombs[j][1], 2));
                if (bombs[i][2] >= dis) adj[i].add(j);
                if (bombs[j][2] >= dis) adj[j].add(i);
            }
        }

        int res = 0;
        for (int i = 0; i < len; i++) {
            res = Math.max(res, dfs(i, new HashSet<>(), adj));
            if (res == len) break;
        }

        return res;
    }

    private int dfs(int i, Set<Integer> visited, List<Integer>[] adj) {
        if (visited.contains(i)) return 0;
        visited.add(i);

        if (adj[i] != null) {
            for (int next : adj[i]) {
                dfs(next, visited, adj);
            }
        }

        return visited.size();
    }
}
