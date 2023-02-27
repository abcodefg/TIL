package programmers;

import java.util.ArrayDeque;
import java.util.Queue;

/*n x m 격자 미로가 주어집니다. 당신은 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출해야 합니다.
* 단, 격자의 바깥으로 나갈 수 없고, 총 이동거리는 k여야 하며,
* 미로에서 탈출한 경로를 문자열로 나타냈을 때 문자열이 사전순으로 가장 빠른 순으로 탈출해야 합니다.
*
* 이동 경로 중 상하좌우는 각각 문자열 'u','d','l','r'로 바꿀 수 있습니다.
*
* 미로의 가로, 세로 길이와 시작 지점 좌표, 탈출지점 좌표, 탈출하기 위해 이동해야 하는 거리가 주어질 때,
* 탈출 경로를 나타낸 문자열을 return하는 solution 함수를 작성하세요.
* 단, 위 조건대로 미로를 탈출할 수 없을 경우 "impossible"을 return해야 합니다.
*
* (그 외의 설명은 링크로 대체한다)
* https://school.programmers.co.kr/learn/courses/30/lessons/150365
 */
public class BFS_미로_탈출_명령어 {
    /**
     * 처음에는 재귀 DFS로 접근했는데 테스트 케이스는 통과했지만, 본 채점에서는 메모리 초과가 떴다.
     *
     * 결국 BFS를 활용한 다른 분의 풀이를 참고했다.
     */
    public int[] dx = {1, 0, 0, -1};
    public int[] dy = {0, -1, 1, 0};
    // TODO 확인할 것 (1) : 객체 활용
    public class Point {
        int x, y;
        StringBuilder sb;

        public Point(int x, int y, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.sb = sb;
        }
    }

    public String getDirection(int i) {
        if (i == 0) return "d";
        if (i == 1) return "l";
        if (i == 2) return "r";
        if (i == 3) return "u";
        return "";
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // TODO 확인할 것 (2) : 좌표와 이동 횟수에 따른 이전 탐색 여부를 기록
        boolean[][][] visited = new boolean[n][m][k + 1];
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x - 1, y - 1, new StringBuilder()));
        visited[x - 1][y - 1][0] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            StringBuilder sb = point.sb;
            // K번 이동했을 때
            if (sb.length() == k) {
                // 탈출지점에 도착했다면 해당 문자열을 반환한다.
                if (point.x == r - 1 && point.y == c - 1)
                    return sb.toString();
                // 도착하지 못했다면 탐색을 이어나간다.
                else
                    continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];
                // 격자를 벗어났거나 이전에 해당 횟수만큼 이동해 현재 지점을 방문한 적이 있다면 넘어간다.
                    // 후자의 경우 이미 사전순으로 더 빠른 이동 경로가 큐를 포함되어 있거나 거쳐갔다.
                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= m
                        || visited[nextX][nextY][sb.length() + 1])
                    continue;

                q.add(new Point(nextX, nextY, new StringBuilder(sb).append(getDirection(i))));
                visited[nextX][nextY][sb.length() + 1] = true;
            }
        }
        return "impossible";
    }
}

