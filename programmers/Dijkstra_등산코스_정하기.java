package programmers;

import java.util.*;

public class Dijkstra_등산코스_정하기 {
    class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private Set<Integer> gateSet = new HashSet<>();
    private Set<Integer> summitSet = new HashSet<>();
    private ArrayList<Node>[] graph;
    private int minSummit = Integer.MAX_VALUE, minIntensity = Integer.MAX_VALUE;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int gate : gates) {
            gateSet.add(gate);
        }
        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int[] path : paths) {
            int a = path[0], b = path[1], cost = path[2];

            if (gateSet.contains(a) || summitSet.contains(b)) {
                graph[a].add(new Node(b, cost));
            } else if (gateSet.contains(b) || summitSet.contains(a)) {
                graph[b].add(new Node(a, cost));
            } else {
                graph[a].add(new Node(b, cost));
                graph[b].add(new Node(a, cost));
            }
        }

        dijkstra(n, gates, summits);

        return new int[]{minSummit, minIntensity};
    }

    private void dijkstra(int n, int[] gates, int[] summits) {
        int[] intensity = new int[n + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(intensity, Integer.MAX_VALUE);

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.cost > intensity[curr.index])
                continue;

            for (Node next : graph[curr.index]) {
                int dist = Math.max(intensity[curr.index], next.cost);
                if (intensity[next.index] > dist) {
                    intensity[next.index] = dist;

                    pq.offer(new Node(next.index, intensity[next.index]));
                }
            }
        }

        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensity[summit] < minIntensity) {
                minSummit = summit;
                minIntensity = intensity[summit];
            }
        }
    }
}
