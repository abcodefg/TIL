package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// #11725 트리의 부모 찾기 풀이
/*N개의 연결된 정점들에 대한 정보를 입력받아
*트리의 루트가 1일 때 2~N번 노드들의 부모 노드 번호를 출력하는 문제*/
public class Tree_11725 {
    // 풀이 자체는 어렵지 않은 문제다.
    // 그러나, 인접행렬은 입력되는 노드의 값이 많을수록 그 개수만큼 반복문을 순회해야 하므로
    // 메모리 사용량이 늘어난다는 점을 간과했고, 인접행렬을 사용한 첫 풀이는 메모리가 초과됐다.
    // 자원이 제한된 상태에서의 풀이는 입력 조건에 따라 섬세함이 필요하다는 걸 새삼 깨달을 수 있었다.

    static ArrayList<Integer>[] adj;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        // TODO 확인할 것 (1): 인접리스트 사용
        adj = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        parent = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y);
            adj[y].add(x);
        }

        dfs(1);
        for(int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }

        parent = new int[N + 1];
        visited = new boolean[N + 1];

        // TODO 확인할 것 (2): 큐를 인자로 받는 재귀 BFS
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        bfs(q);
        for(int i = 2; i <= N; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(int current) {
        if(visited[current]) {
            return;
        }
        visited[current] = true;
        for(int child : adj[current]) {
            if (!visited[child]) {
                parent[child] = current;
                dfs(child);
            }
        }
    }

    static void bfs(Queue<Integer> q) {
        if(q.isEmpty()) {
            return;
        }
        int current = q.poll();
        visited[current] = true;

        for(int i : adj[current]) {
            if(!visited[i]) {
                parent[i] = current;
                q.offer(i);
            }
        }
        bfs(q);
    }
}

