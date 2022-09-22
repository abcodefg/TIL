package baekjoon;

import java.util.Scanner;

//#10971 외판원 순회 2
public class BF_10971 {
    static int min, N;
    static boolean[] visited;
    static int[][] adj;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        min = 10000000;
        visited = new boolean[N];
        adj = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                adj[i][j] = sc.nextInt();
            }
        }

        visited[0] = true;
        dfs(0, 0, 0, 0);

        System.out.println(min);
    }

    static void dfs(int start, int current, int move, int cost) {
        if (current == start && cost > 0) {
            min = Math.min(cost, min);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (adj[current][i] != 0) {
                if (i == start && move == N - 1) {
                    dfs(start, i, move + 1, cost + adj[current][i]);
                }
                else if (!visited[i]) {
                    visited[i] = true;
                    dfs(start, i, move + 1, cost + adj[current][i]);
                    visited[i] = false;
                }
            }
        }
    }
}
