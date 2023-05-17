package programmers.아이템_줍기;

import java.util.*;

public class Solution {
    private int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[101][101];

        for (int[] r : rectangle) {
            init(2 * r[0], 2 * r[1], 2 * r[2], 2 * r[3]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        boolean[][] visited =  new boolean[101][101];
        int itemx = 2 * itemX, itemy = 2 * itemY;
        int ans = Integer.MAX_VALUE;
        q.add(2 * characterX);
        q.add(2 * characterY);

        while (!q.isEmpty()) {
            int x = q.poll();
            int y = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if (map[nx][ny] != 1 || visited[nx][ny]) continue;

                map[nx][ny] = map[x][y] + 1;
                if (nx == itemx && ny == itemy) {
                    ans = Math.min(ans, map[nx][ny]);
                    continue;
                }
                visited[nx][ny] = true;
                q.add(nx);
                q.add(ny);
            }
        }

        return ans / 2;
    }

    private void init(int x1, int y1, int x2, int y2) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                if (map[x][y] == 2) continue;
                map[x][y] = 2;
                if (x == x1 || x == x2 || y == y1 || y == y2) {
                    map[x][y] = 1;
                }
            }
        }
    }

    public int solution_failed(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        Queue<Coordinate> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[51][51];
        q.add(new Coordinate(characterX, characterY, 0));
        visited[characterX][characterY] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            Coordinate cur = q.poll();
            if (cur.x == itemX && cur.y == itemY) {
                return cur.dis;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if (!visited[nextX][nextY] && onOuterEdge(cur.x, cur.y, nextX, nextY, rectangle)) {
                    visited[nextX][nextY] = true;
                    q.add(new Coordinate(nextX, nextY, cur.dis + 1));
                }
            }
        }

        return 0;
    }

    private boolean onOuterEdge(int curX, int curY, int x, int y, int[][] rectangle) {
        boolean res = false;
        for (int[] r : rectangle) {
            if (x < r[0] || x > r[2] || y < r[1] || y > r[3]) {
                continue;
            } else if (x > r[0] && x < r[2] && y > r[1] && y < r[3]) {
                return false;
            } else if (curX >= r[0] && curX <= r[2] && curY >= r[1] && curY <= r[3] &&
                    (curX == r[0] || curX == r[2] || curY == r[1] || curY == r[3])){
                res = true;
            }
        }

        return res;
    }

    static class Coordinate {
        int x;
        int y;
        int dis;

        public Coordinate(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
