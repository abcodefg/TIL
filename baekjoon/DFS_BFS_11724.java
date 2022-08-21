package baekjoon;

import java.io.*;
import java.util.*;

// #11724 연결 요소의 개수 풀이
/*방향 없는 그래프가 주어졌을 때, 연결 요소(Connected components)의 개수를 구하는 문제*/
public class DFS_BFS_11724 {
    // DFS 혹은 BFS를 통해 그 정점과 연결된 정점을 모두 순회하며 방문여부를 true로 바꾸어준다.

    static int[][] edges;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        edges = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        // 인접행렬을 만든다.
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edges[x][y] = 1;
            edges[y][x] = 1;
        }

        int dfsCount = 0;
        int bfsCount = 0;

        // 정점들을 순회하면서 어떤 정점을 방문하지 않은 경우,
        // 해당 정점과 연결된 정점들을 탐색하고
        // 탐색이 끝나면 count를 늘린다.
        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                dfs(i);
                dfsCount++;
            }
        }
        System.out.println(dfsCount);

        visited = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            if(!visited[i]) {
                bfs(i);
                bfsCount++;
            }
        }
        System.out.println(bfsCount);
    }

    // 정점을 입력받아 그래프를 탐색하며 연결된 정점들을 모두 방문한 것으로 기록한다.
    // 그래프 탐색 알고리즘으로는 깊이우선탐색(DFS)과 너비우선탐색(BFS)을 사용한다.
    static void dfs(int vertex) {
        Stack<Integer> stack = new Stack<>();
        stack.push(vertex);

        while(!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                for(int i = 1; i < edges.length; i++) {
                    if(edges[current][i] == 1 & !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
    }

    static void bfs(int vertex) {
        Queue<Integer> que = new LinkedList<>();
        que.add(vertex);

        while(!que.isEmpty()) {
            int current = que.poll();
            if (!visited[current]) {
                visited[current] = true;
                for(int i = 1; i < edges.length; i++) {
                    if(edges[current][i] == 1 & !visited[i]) {
                        que.add(i);
                    }
                }
            }
        }
    }
}
