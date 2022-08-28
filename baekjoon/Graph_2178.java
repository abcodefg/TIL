package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// #2178 미로탐색 풀이
/*1과 0으로 이루어진 미로를 형상화한 N * M 크기의 배열을 입력받는다.
* 1, 0은 각각 이동할 수 있는 칸, 없는 칸이다.
* (1, 1) 위치에서 출발해 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 문제*/
public class Graph_2178 {
    // 기존 너비우선탐색(bfs) 문제와 비슷한 유형이다.
    // 모든 경우의 수를 탐색주기(한 지점에서 이동할 수 있는 경우의 수)별로 나누어서 확인하고,
    // 조건을 처음 만족하는 최소의 주기를 구하는 문제이기 때문이다.
    // 다른 점이 있다면 주기를 따로 변수를 두어 count하지 않고,
    // 입력받은 배열에 저장한다는 점이다.
    // 이는 아래 '#중요'로 표시한 부분에서 확인할 수 있다.
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        bfs();
        // #중요 -- (a)
        System.out.println(arr[N - 1][M - 1]);
    }

    static void bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0, 0});

        // 현재 지점에서 이동할 수 있는 네 방향의 지점을 모두 탐색한다.
        while (!que.isEmpty()) {
            int curX = que.peek()[0];
            int curY = que.peek()[1];
            visited[curX][curY] = true;
            que.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                // 다음 지점이 미로 안에 있고
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                    // 방문한 적 없으며, 값이 1이라면
                    if (!visited[nextX][nextY] && arr[nextX][nextY] == 1) {
                        // 카운트를 늘리고 그 지점에서 탐색을 이어간다.
                        // #중요 -- (b)
                        arr[nextX][nextY] = arr[curX][curY] + 1;
                        que.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }
}
