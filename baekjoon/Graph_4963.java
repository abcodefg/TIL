package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #4963 섬의 개수 풀이
/*1과 0(땅과 바다)으로 이루어진 지도를 입력받아 섬의 개수를 구하는 문제
* 단, 땅이 대각선으로 연결된 경우에도 하나로 본다.*/

public class Graph_4963 {
    // 2667번과 유사한 문제.
    // 상하좌우가 아닌 대각선도 확인해야 한다는 점이 다르다.
    // 전역변수가 지저분하게 많다는 게 흠.
    // 다만, 풀이방식을 바꾸지 않는 한, 전역변수를 줄이면 필연적으로 메서드의 인자가 많아진다.
    static int w;
    static int h;
    static int[] dy = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dx = {0, 0, -1, 1, -1, 1, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) {
                break;
            }
            map = new int[h][w];
            visited = new boolean[h][w];
            int count = 0;

            for(int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(!visited[i][j] && map[i][j] == 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    static void dfs(int y, int x) {
        if(visited[y][x]) {
            return;
        }
        visited[y][x] = true;

        for(int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny >= 0 && nx >= 0 && ny < h && nx < w) {
                if(!visited[ny][nx] && map[ny][nx] == 1) {
                    dfs(ny, nx);
                }
            }
        }
    }
}
