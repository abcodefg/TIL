package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// #1707 이분 그래프 풀이
/*그래프에 속한 모든 정점을 두 개의 인접하지 않은 정점들의 집합으로 나눌 수 있을 때
*이러한 그래프를 이분 그래프라고 한다.
* 테스트 케이스의 개수, 그래프의 정점과 간선의 개수,간선에 대한 정보를 입력받아
* 이분그래프면 YES, 아니면 NO를 출력하는 문제*/
public class Graph_1707 {
    // 그래프를 탐색하며 정점들에 1, 2 중 하나의 표지를 붙인다.
    // 시작점에는 1을 붙이고 인접한 정점들에는 2, 또 이 정점들과 인접한 정점들에는 1...
    // 이런 식으로 인접한 정점들에 다른 표지를 번갈아서 붙인다.
    // 이후 어떤 정점과 인접한 정점에 같은 표지가 붙어있다면 NO를 반환한다.
    static int V, E;
    static int[] visited;
    static ArrayList<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            visited = new int[V + 1];
            adj = new ArrayList[V + 1];
            for(int j = 0; j < V + 1; j++) {
                adj[j] = new ArrayList<Integer>();
            }
            for(int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adj[x].add(y);
                adj[y].add(x);
            }
            System.out.println(bfs());
        }
    }

    static String bfs() {
        Queue<Integer> que = new LinkedList<>();
        // 그래프가 끊어져있을 수 있으므로
        // 탐색을 마쳤음에도 아직 방문하지 않은 노드들이 있다면 탐색을 반복한다.
        for(int i = 1; i < V + 1; i++) {
            if(visited[i] == 0) {
                que.add(i);
                visited[i] = 1;
            }
            while(!que.isEmpty()) {
                int current = que.poll();
                if(visited[current] != 0) {
                    for(int j : adj[current]) {
                        if(visited[j] == 0) {
                            if(visited[current] == 1) visited[j] = 2;
                            else if(visited[current] == 2) visited[j] = 1;
                            que.add(j);
                        } else {
                            if(visited[current] == visited[j])
                                return "NO";
                        }
                    }
                }
            }
        }

        return "YES";
    }
}
