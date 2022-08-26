package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// #2667 단지번호붙이기 풀이
/*0 혹은 1로 이루어진 N*N 크기의 지도를 형상화한 문자열을 입력받는다.
* 이 때, 0과 1은 집이 없는 곳과 있는 곳을 나타낸다.
* 가로 혹은 세로로 맞닿아있는 집들의 모음을 단지라고 할 때,
* 단지의 개수와 오름차순으로 정렬한 크기를 반환하는 문제*/
public class Graph_2667 {
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static ArrayList<Integer> list;
    static int N;
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        arr = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();
        boolean streak = false;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 1 && !visited[i][j]) {
                    count = 1;
//                    dfs(i, j);
                    bfs(i, j);
                    list.add(count);
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        for (int i : list) {
            System.out.println(i);
        }
    }

    static void dfs(int x, int y) {
        visited[x][y] = true;
        // 확인할 부분 (1) : 좌표 이동을 두 개의 int 배열과 하나의 반복문으로 처리
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    dfs(nextX, nextY);
                    count++;
                }
            }

        }
    }

    static void bfs(int x, int y) {
        visited[x][y] = true;
        // 확인할 부분 (2) : 큐에 좌표 담기
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x, y});

        while (!que.isEmpty()) {
            int curX = que.peek()[0];
            int curY = que.peek()[1];
            que.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                    if (arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        que.add(new int[]{nextX, nextY});
                        count++;
                    }
                }

            }
        }
    }
}