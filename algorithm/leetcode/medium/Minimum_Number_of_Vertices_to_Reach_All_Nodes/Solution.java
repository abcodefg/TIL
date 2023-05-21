package algorithm.leetcode.medium.Minimum_Number_of_Vertices_to_Reach_All_Nodes;

import java.util.*;

public class Solution {
    /**
     * [인접 리스트를 활용한 풀이]
     * 다른 모든 정점들에 접근할 수 있는 최소 개수의 정점의 집합은 각 정점들까지의 경로의 시작점이 되는 정점들의 집합이다.
     * 이를 구하기 위해선 각 정점들을 경로의 역순으로 끝까지 타고 올라가면 된다.
     * 간선의 방향을 반대로 뒤집으면 경로의 시작 지점은 끝 지점이 되므로, 인접 리스트를 거꾸로 구성한 후, 뻗어나갈 수 있는 정점이 더는 없는 끝 정점의 인덱스를 구한다.
     *
     * Time : O(n + m) (n -> 정점의 수 = n, m -> 간선의 수 = edges.size())
     * Space : O(n + m)
     * Runtime : 24 ms (26.15%)
     * Memory : 86.9 MB (33.72%)
     */
    public List<Integer> findSmallestSetOfVertices_adjacencyList(int n, List<List<Integer>> edges) {
        List<Integer>[] adj = new ArrayList[n];
        List<Integer> ans = new ArrayList<>();

        for (List<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            if (adj[to] == null) {
                adj[to] = new ArrayList<>();
            }

            adj[to].add(from);
        }

        for (int i = 0; i < n; i++) {
            if (adj[i] == null)
                ans.add(i);
        }

        return ans;
    }

    /**
     * [간선을 활용한 풀이]
     * 어떤 경로의 시작점이 되는 정점 x는 어떤 간선의 도착 정점일 수 없다.
     * 만약 x가 어떤 간선의 도착 정점이라면, x가 아닌 해당 간선의 출발 정점이 전체 경로의 시작점이기 때문이다.
     *
     * 따라서, 모든 간선의 도착 정점을 표시하고, 어떤 간선의 도착 정점도 아닌, 경로의 시작점들을 기록한다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 24 ms (26.15%)
     * Memory : 86.9 MB (33.72%)
     */
    public List<Integer> findSmallestSetOfVertices_booleanArray(int n, List<List<Integer>> edges) {
        boolean[] dest = new boolean[n];
        List<Integer> ans = new ArrayList<>();

        for (List<Integer> edge : edges) {
            dest[edge.get(1)] = true;
        }

        for (int i = 0; i < n; i++) {
            if (!dest[i])
                ans.add(i);
        }

        return ans;
    }
}
