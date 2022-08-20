package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// graph DFS & BFS
    // 정점간의 연결관계를 정리하는 방법으로 인접리스트와 인접행렬가 있다.
    // 이번엔 인접행렬을 사용했다.
public class DFS_BFS_graph {
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        // 인접행렬을 만든다.
        edges = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            edges[x][y] = 1;
            edges[y][x] = 1;
        }
        // 정점들의 방문 여부를 확인하는 배열을 만든다.
        boolean[] visited = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();

        // DFS와 BFS의 결과값을 각각 공백을 사이에 두고 연결된 문자열로 만들어 반환한다.
        for(int i : dfs(V, visited)) {
            sb.append(i).append(" ");
        }
        sb.append('\n');
        visited = new boolean[N + 1];
        for(int i : bfs(V, visited)) {
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }

    static ArrayList<Integer> dfs(int start, boolean[] visited) {

        // graph의 DFS는 재귀를 사용하는 방법과 재귀 없이 스택을 사용하는 방법이 있다.
        // 이번엔 스택을 사용했다.
        ArrayList<Integer> dfsList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while(!stack.isEmpty()) {
            int current = stack.pop();
            if(!visited[current]) {
                visited[current] = true;
                dfsList.add(current);
                for(int i = visited.length - 1; i >= 1; i--) {
                    if(edges[current][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return dfsList;
    }

    static ArrayList<Integer> bfs(int start, boolean[] visited) {

        // graph의 BFS는 큐를 사용한다.
        ArrayList<Integer> bfsList = new ArrayList<>();
        Queue<Integer> que = new LinkedList<>();
        que.add(start);

        while (!que.isEmpty()) {
            int current = que.poll();
            if(!visited[current]) {
                visited[current] = true;
                bfsList.add(current);
                for(int i = 1; i < visited.length; i++) {
                    if(edges[current][i] == 1 && !visited[i]) {
                        que.add(i);
                    }
                }
            }
        }
        return bfsList;
    }
}
