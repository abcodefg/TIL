package programmers.일_이_삼_떨어트리기;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] edges = {{2, 4}, {1, 2}, {6, 8}, {1, 3}, {5, 7}, {2, 5}, {3, 6}, {6, 10}, {6, 9}};
        int[] target = {0, 0, 0, 3, 0, 0, 5, 1, 2, 3};
        System.out.println(Arrays.toString(solution(edges, target)));
    }
    private static int n;
    private static int[] pointer;
    private static List<Integer>[] adj;
    private static List<Integer>[] order;
    private static int[] ans;
    public static int[] solution(int[][] edges, int[] target) {
        n = 1;
        pointer = new int[target.length];
        adj = new ArrayList[target.length + 1];
        order = new ArrayList[target.length + 1];

        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            if (adj[a] == null) {
                adj[a] = new ArrayList<>();
            }
            adj[a].add(b);
        }

        for (int i = 0; i < target.length; i++) {
            order[i + 1] = new ArrayList<>();
            if (adj[i + 1] != null) {
                Collections.sort(adj[i + 1]);
            }
        }

        outer:
        while (true) {
            int i = 1;
            while (adj[i] != null) {
                int child = adj[i].get(pointer[i]);
                pointer[i] = pointer[i] < adj[i].size() - 1 ? pointer[i] + 1 : 0;
                i = child;
            }

            order[i].add(n++);
            for (int j = 1; j < order.length; j++) {
                int len = order[j].size();
                if (len > target[j - 1])
                    return new int[]{-1};
                else if (3 * len >= target[j - 1] && j == order.length - 1)
                    break outer;
            }
        }

        ans = new int[n];
        for (int i = 0; i < target.length; i++) {
            if (target[i] == 0) continue;
            int node = i + 1;
            int len = order[node].size();
            int t = target[i];

            for (int j = 0; j < len; j++) {
                int num = 1;
                while ((num + (len - (j + 1)) * 3) < t) {
                    num++;
                }
                ans[order[node].get(j)] = num;
                t -= num;
            }
        }
        return ans;
    }
}
