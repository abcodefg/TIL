package algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

//robotPath2
/*세로와 가로의 길이가 m, n인 지도가 2차원 배열로 주어진다.
* 그리고 1은 장애물, 0은 이동 가능한 통로를 의미한다.
* 임의의 k칸 직진과 90도 회전 중 1가지 동작을 할 수 있다.
* 로봇의 현재 위치와 방향, 목표 위치와 방향이 주어질 때,
* 로봇이 목표지점까지 이동해 목표 방향으로 회전하기 위해 필요한 최소의 동작 수를 구하라.*/
public class BFS_graph {
    //지도를 나타내는 2차원 배열이 주어지고
    //그 위에서 일어나는 칸 단위로 이루어지는 변화를
    //bfs를 활용해서 기록, 추적, 확인하는 유형의 전형적인 문제다.
    public int robotPath2(int[][] room, int[] src, int sDir, int[] dst, int dDir) {
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{src[0], src[1], sDir});



        int moves = 0;
        int[][] dirArr = new int[][]{{0, 0}, {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!que.isEmpty()) {
            int curY = que.peek()[0];
            int curX = que.peek()[1];
            int dir = que.peek()[2];
            moves = room[curY][curX];
            //목표지점에 도달했다면 방향을 전환하고 탐색을 종료한다.
            if(curY == dst[0] && curX == dst[1]) {
                if(dDir != dir) {
                    room[curY][curX] += turn(dir, dDir);
                }
                break;
            }
            que.poll();

            for(int nextDir = 1; nextDir <= 4; nextDir++) {
                int nextY = curY + dirArr[nextDir][0];
                int nextX = curX + dirArr[nextDir][1];
                int nextMoves = moves + turn(dir, nextDir) + 1;
                if(nextX == src[1] && nextY == src[0]) continue;
                while(nextX >= 0 && nextY >= 0 && nextX < room[0].length && nextY < room.length
                        && room[nextY][nextX] == 0) {
                    que.add(new int[]{nextY, nextX, nextDir});
                    room[nextY][nextX] = nextMoves;

                    nextY += dirArr[nextDir][0];
                    nextX += dirArr[nextDir][1];
                }
            }
        }
        return room[dst[0]][dst[1]];
    }
    static int turn(int dir, int nextDir) {
        return (Math.abs(dir - nextDir) == 3) ? 1 : Math.abs(dir - nextDir);
    }
}
