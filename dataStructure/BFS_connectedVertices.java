package dataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class BFS_connectedVertices {

    public static void main(String[] args) {
        // 어떤 그래프의 간선에 대한 정보를 담은 배열. 이때 간선은 방향이 없다.
        // 연결된 정점들로 구성된 컴포넌트의 개수를 구해보자.
        int[][] edges = {
                {0, 1},
                {2, 3},
                {3, 4},
                {3, 5}
        };
        System.out.println(connectVertices(edges));
    }

    public static int connectVertices(int[][] edges) {
        // 배열에 입력된 정점들 중 최대값을 찾고,
        int biggest = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                biggest = Math.max(biggest, edges[i][j]);
            }
        }
        // 0번부터 최대값까지 담을 수 있는 정방의 빈 인접행렬을 생성한다.
        int[][] matrix = new int[biggest + 1][biggest + 1];

        // 입력받은 배열에 있는 간선에 대한 정보를 토대로 정점들의 연결관계를 인접행렬에 기록한다.
        for (int i = 0; i < edges.length; i++) {
            matrix[edges[i][0]][edges[i][1]] = 1;
            matrix[edges[i][1]][edges[i][0]] = 1;
        }

        // 정점들의 방문여부를 확인하는 배열 생성한다.
        boolean[] visited = new boolean[biggest + 1];
        int count = 0;

        // 정점들을 순회하며 방문여부를 체크한다.
        for(int i = 0; i < visited.length; i++) {
            // 어떤 정점을 방문한 적이 없다면
            if(!visited[i]) {
                // 해당 컴포넌트에 속한 정점들을 모두 방문한 상태로 바꾸어주고
                visited = bfs(matrix, visited, i);
                // 탐색한 컴포넌트의 개수를 늘려준다.
                count++;
            }
        }
        // 컴포넌트의 개수를 반환한다.
        return count;
    }

    // 입력받은 어떤 정점과 연결된 다른 정점들을 모두 방문한 상태로 변경하는 메서드
    // 그 정점이 속한 컴포넌트를 체크아웃하는 역할을 수행한다.
    public static boolean[] bfs(int[][] matrix, boolean[] visited, int vertex) {
        // 큐를 생성
        Queue<Integer> queue = new LinkedList<>();
        // 큐에 정점 추가
        queue.add(vertex);
        // 방문한 정점이라는 것 기록
        visited[vertex] = true;

        // 큐가 0이 되어야 멈추는 반복문
        while(queue.size() != 0) {
            // 큐를 비워주면서 이를 현재 탐색하는 정점으로 지정
            int now = queue.poll();
            // 인접행렬의 현재 정점의 다른 정점과의 연결관계를 나타내는 배열을 순회
            // 연결된 모든 정점의 방문상태를 true로 바꾸어주는 역할을 수행한다.
            for (int i = 0; i < matrix.length; i++) {
                // 만약 어떤 정점이 연결되어있고, 방문한 적이 없다면
                if(matrix[now][i] == 1 && !visited[i]) {
                    // 큐에 해당 정점을 넣어주고 (이를 통해,
                    queue.add(i);
                    // 정점을 방문했다는 것을 기록한다.
                    visited[i] = true;
                }
            }
        }
        // 업데이트된 정점들의 방문여부에 대한 정보를 반환한다.
        return visited;
    }
}