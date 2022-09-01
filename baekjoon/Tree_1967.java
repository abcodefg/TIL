package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// #1967 트리의 지름 풀이
// TODO 확인할 것 (1): 트리의 특징
// 트리는 사이클이 없는 무방향 그래프이다.
// 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 하나만 존재하게 된다.
/*트리에 존재하는 모든 경로 중에 가장 긴 경로의 길이를 트리의 지름이라고 한다.
* 간선에 가중치가 있는 트리의 인접관계를 입력받아 트리의 지름을 구하는 문제*/
public class Tree_1967 {

    // 자식 노드가 없는 노드를 임의로 '끝 노드'라고 부르기로 하자.
    // 그래프의 최장경로 X의 양 끝을 이루는 노드 A 혹은 노드 B가 끝 노드가 아니라고 가정하자.
    // 모든 끝 노드가 아닌 노드는 어떤 끝 노드와 끝 노드의 사이에 있다.
    // 그런데 X에 A 혹은 B로부터 경로에 포함되지 않은 끝 노드까지의 거리를 더하면 X의 길이보다 크다.
    // 이는 앞선 가정에 모순이므로, 최장거리의 양 끝을 이루는 노드는 끝 노드여야 한다.

    // 따라서 이 문제는 끝 노드에서 시작하는 dfs를 통해 최대값을 찾으면 된다.
    // 그런데 모든 끝 노드에서 최장거리를 탐색하는 건 비효율적이다.
    // 따라서, 1에서 시작하는 dfs로 거리가 가장 먼 끝 노드 maxNode를 찾고
    // maxNode에서 시작하는 dfs를 통해 최장거리를 찾았다.

    static int max, maxNode;
    static class Node {
        int to;
        int len;

        public Node(int to, int len) {
            this.to = to;
            this.len = len;
        }
    }
    static ArrayList<Node>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        // TODO 확인할 것 (2): 배열은 완전히 채워줄 것!
        //  0은 필요없을 것 같아 i를 1로 했다가 NullPointerException이 발생했다.
        for (int i = 0; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            adj[from].add(new Node(to, len));
            adj[to].add(new Node(from, len));
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0, visited);

        visited = new boolean[n + 1];
        visited[maxNode] = true;
        dfs(maxNode, 0, visited);

        System.out.println(max);
    }

    static void dfs(int current, int length, boolean[] visited) {
        // TODO 확인할 것 (3): 재귀의 종료조건을 정하지 않았다.
        if(length > max) {
            max = length;
            maxNode = current;
        }

        for(Node child : adj[current]) {
            if(!visited[child.to]) {
                visited[child.to] = true;
                dfs(child.to, length + child.len, visited);
            }
        }
    }
}
