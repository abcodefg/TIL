package algorithm.leetcode.hard.Largest_Color_Value_in_a_Directed_Graph;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String colors = "abaca";
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};
        System.out.println(largestPathValue(colors, edges));
    }
    private static List<Integer>[] adj;
    private static boolean[] visited;
    private static int[][] count;
    private static Set<Integer> path = new HashSet<>();
    public static int largestPathValue(String colors, int[][] edges) {
        if (edges.length == 0) {
            return 1;
        }
        adj = new ArrayList[colors.length()];
        Arrays.fill(adj, new ArrayList<>());

        for(int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
        }

        int res = 0, n = colors.length();
        visited = new boolean[n];
        count = new int[n][26];
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(i, colors));
            path.clear();
        }

        return res = res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int dfs(int i, String colors) {
        if (path.contains(i)) {
            return Integer.MAX_VALUE;
        }
        if (visited[i]) {
            return 0;
        }

        path.add(i);
        visited[i] = true;

        int colorIdx = colors.charAt(i) - 'a';
        count[i][colorIdx] = 1;

        int max = 0;
        for (int next : adj[i]) {
            if (dfs(next, colors) == Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            for (int color = 0; color < 26; color++) {
                int cur = color == colorIdx ? 1 : 0;
                count[i][color] = Math.max(count[i][color], cur + count[next][color]);

                max = Math.max(max, count[i][color]);
            }
        }
        return max;
    }
}
