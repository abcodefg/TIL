package algorithm.leetcode.medium.Evaluate_Division;

import java.util.*;

public class Solution {
    /**
     * [그래프를 응용한 풀이]
     * 이 문제는 각 equation의 두 요소(나누는 수, 나누어지는 수)를 그래프의 정점으로, equation의 value를 간선의 가중치로 하는
     * 가중 그래프(weighted graph)로 치환하면 보다 쉽게 접근할 수 있다.
     *
     * 예를 들어, a/b = 2.0이고 b/c = 3.0이라면 a/c은 a/b * b/c = 2.0 * 3.0 = 6.0과 같은 방식으로 구할 수 있다.
     * 이는 곧, 그래프의 서로 연결된 노드 a ->(2.0)-> b ->(3.0)-> c를 지나가는 것으로 볼 수 있다.
     * 다만 a/b = 2.0이라면 b/a는 1 / 2.0이므로 간선의 반대 방향의 가중치는 본래 가중치의 역수로 삼아야 한다.
     *
     * Time : O(N * (V + E)) (N = queries.size(), V = 그래프 정점의 개수, E = 간선의 개수)
     * Space : O(V + E)
     * Runtime : 1 ms (90.42%)
     * Memory : 41.6 MB (16.72%)
     */
    static class Edge {
        String dest;
        double value;

        public Edge(String dest, double value) {
            this.dest = dest;
            this.value = value;
        }
    }

    Map<String, List<Edge>> map;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String source = equations.get(i).get(0);
            String dest = equations.get(i).get(1);
            List<Edge> listA = map.getOrDefault(source, new ArrayList<>());
            List<Edge> listB = map.getOrDefault(dest, new ArrayList<>());
            listA.add(new Edge(dest, values[i]));
            listB.add(new Edge(source, 1 / values[i]));

            map.put(source, listA);
            map.put(dest, listB);
        }

        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            ans[i] = bfs(queries.get(i).get(0), queries.get(i).get(1));
        }

        return ans;
    }

    private double bfs(String source, String dest) {
        if (!map.containsKey(source) || !map.containsKey(dest)) return -1.0;

        Queue<Edge> q = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        q.add(new Edge(source, 1.0));
        visited.add(source);

        while (!q.isEmpty()) {
            Edge cur = q.poll();
            if (cur.dest.equals(dest)) {
                return cur.value;
            }

            if (!map.containsKey(cur.dest)) continue;

            for (Edge next : map.get(cur.dest)) {
                if (!visited.contains(next.dest)) {
                    visited.add(next.dest);
                    q.add(new Edge(next.dest, cur.value * next.value));
                }
            }
        }

        return -1.0;
    }
}
