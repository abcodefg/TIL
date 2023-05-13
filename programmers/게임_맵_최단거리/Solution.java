package programmers.게임_맵_최단거리;

import java.util.Queue;
import java.util.ArrayDeque;

public class Solution {
    public int solution(int[][] maps) {
        int rows = maps.length;
        int cols = maps[0].length;
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        boolean[][] visited = new boolean[rows][cols];

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0});
        visited[0][0] = true;

        int ans = 0;

        outer:
        while (!que.isEmpty()) {
            int n = que.size();
            ans++;

            // x번째로 이동한 위치를 모두 탐색
            for (int i = 0; i < n; i++) {
                int[] cur = que.poll();
                // 현재 위치가 상대팀 진영이라면 탐색 종료
                if (cur[0] == rows - 1 && cur[1] == cols - 1) break outer;

                for (int j = 0; j < 4; j++) {
                    int newR = cur[0] + dr[j];
                    int newC = cur[1] + dc[j];
                    if (newR < 0 || newC < 0 || newR >= rows || newC >= cols
                            || visited[newR][newC] || maps[newR][newC] == 0) continue;
                    visited[newR][newC] = true;
                    que.add(new int[]{newR, newC});
                }
            }
        }

        return visited[rows - 1][cols - 1] ? ans : -1;
    }
}
