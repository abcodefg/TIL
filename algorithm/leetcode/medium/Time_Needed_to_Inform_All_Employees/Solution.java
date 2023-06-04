package algorithm.leetcode.medium.Time_Needed_to_Inform_All_Employees;

import java.util.*;

public class Solution {
    /**
     * [BFS 풀이]
     */
    public int numOfMinutes_BFS(int n, int headID, int[] manager, int[] informTime) {
        int[] time = new int[n];
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (manager[i] != -1) {
                adj[manager[i]].add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(headID);
        int ans = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            ans = Math.max(ans, time[cur]);

            for (int next : adj[cur]) {
                time[next] = time[cur] + informTime[cur];
                q.add(next);
            }
        }

        return ans;
    }

    /**
     * [DFS 풀이]
     */
    public int numOfMinutes_DFS(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] subs = new List[n];
        for(int i=0;i<n;i++) {
            subs[i] = new ArrayList<>();
        }
        for(int i=0;i<n;i++) {
            int mana = manager[i];
            if(mana!=-1) {
                subs[mana].add(i);
            }
        }
        return dfs(headID, subs, informTime);
    }

    private int dfs(int cur, List<Integer>[] subs, int[] informTime) {
        int max = 0;
        for(int sub : subs[cur]) {
            max = Math.max(max, dfs(sub, subs, informTime));
        }
        return informTime[cur] + max;
    }
}
