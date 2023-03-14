package programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
* https://school.programmers.co.kr/learn/courses/30/lessons/49994
 */
public class DFS_방문_길이 {
    /**
     * 나는 재귀 dfs로 접근했다.
     * 다른 dfs와 굳이 다른 게 있다면 visited 배열을 좌표 + 방향에 대해 나타내기 위해서 3차원으로 생성했다는 점이다.
     *
     * 통과는 하긴 했지만 너무 기계적인 접근인 데다가, 부수적으로 선언해야 하는 변수들이 많았고
     * 특히 인접해 있는 다른 두 좌표의 반대 방향으로의 이동은 같은 길을 지나가므로
     * visited 값을 개별적으로 표현할 필요가 없다는 점에서 visited 배열이 불필요하게 컸다.
     *
     * Set을 활용한 멋진 풀이가 있어서 같이 기록한다.
     * 같은 길을 지나가는 이동을 자료구조와 간단한 연산으로 간결하게 세는 멋진 해법이다.
     */
    public int[] dx = {-1, 0, 0, 1};
    public int[] dy = {0, -1, 1, 0};
    public boolean[][][] visited = new boolean[11][11][4];
    public char[] dir;
    public int answer = 0;
    public int solution(String dirs) {

        dir = dirs.toCharArray();
        dfs(0, 5, 5);
        return answer;
    }

    public void dfs(int n, int x ,int y) {
        if (n == dir.length) {
            return;
        }

        int idx = 0;

        switch (dir[n]) {
            case 'L' :
                idx = 0;
                break;
            case 'U' :
                idx = 1;
                break;
            case 'D' :
                idx = 2;
                break;
            case 'R' :
                idx = 3;
                break;
        }

        int nextX = x + dx[idx];
        int nextY = y + dy[idx];

        if (nextX >= 0 && nextY >= 0 && nextX < 11 && nextY < 11) {
            if (!visited[x][y][idx] && !visited[nextX][nextY][3 - idx]) {
                visited[x][y][idx] = true;
                answer++;
            }
            dfs(n + 1, nextX, nextY);
        } else {
            dfs(n + 1, x, y);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution2("ULURRDLLU"));
    }

    public static int solution2(String dirs) {
        Map<Character, int[]> map = new HashMap<>();
        map.put('U', new int[]{0, -1});
        map.put('D', new int[]{0, 1});
        map.put('L', new int[]{-1, 0});
        map.put('R', new int[]{1, 0});

        Set<String> set =  new HashSet<>();
        int x = 0, y = 0, nx, ny;
        char[] dirArr = dirs.toCharArray();

        for (int i = 0; i < dirArr.length; i++) {
            nx = x + map.get(dirArr[i])[0];
            ny = y + map.get(dirArr[i])[1];

            if (nx >= -5 && ny >= -5 && nx <= 5 && ny <= 5) {
                set.add(x + "" + y + "" + nx + "" + ny);
                set.add(nx + "" + ny + "" + x + "" + y);
                x = nx;
                y = ny;
            }
        }

        return set.size() / 2;
    }
}
