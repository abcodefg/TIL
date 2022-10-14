package baekjoon;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

//#1261 알고스팟
/*문제 설명은 사이트 참고. https://www.acmicpc.net/problem/1261*/
//우선순위 큐의 사용한 다익스트라 알고리즘 개선
    //https://techblog-history-younghunjo1.tistory.com/248
public class Dijkstra_1261 {
    /**
     * 비록 다른 분의 풀이를 참고하긴 했지만, 다익스트라 알고리즘을 처음 사용해봤다.
     * 다익스트라 알고리즘을 구현하는 것도 물론 중요하지만,
     * 이 문제가 다익스트라 알고리즘을 사용할 수 있는 조건을 갖추었다는 걸 알아채는 것이 더 어렵고 우아해보였다.
     *
     * 미로의 각 지점을 vertex로 보고
     * 인접한 vertex로 이동하기 위해 부수어야 하는 벽을 edge의 가중치로 본다면
     * 벽을 최소한으로 부수며 목적지까지 이동해야 하는 이 문제는
     * 가중치가 있는 그래프에서 최단 경로로 이동하는 문제로 생각할 수 있다.
     */
    static int M, N;
    static int[] dm = {-1,1,0,0};
    static int[] dn = {0,0,-1,1};
    static char[][] maze;
    static int[][] dist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        sc.nextLine();
        maze = new char[N][M];
        dist = new int[N][M];
        for(int i = 0; i < N; i++) {
            maze[i] = sc.nextLine().toCharArray();
            //TODO 확인할 것 (1): 각 정점까지의 거리 초기화
            // 출발지점으로부터 각 정점까지의 거리를 최대값으로 설정한다.
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dijkstra(0, 0);
        System.out.println(dist[M - 1][N - 1]);
    }

    static void dijkstra(int m, int n) {
        PriorityQueue<Point> que = new PriorityQueue<>();
        dist[m][n] = 0;
        que.offer(new Point(m,n,0));

        while(!que.isEmpty()) {
            Point curr = que.poll();

            for(int i = 0; i < 4; i++) {
                int nextM = curr.m + dm[i];
                int nextN = curr.n + dn[i];
                int nextCount = curr.count;
                if (nextM >= 0 && nextN >= 0 && nextM < M && nextN < N) {
                    if (maze[nextN][nextM] == '1')
                        nextCount++;
                    //TODO 확인할 것 (2): 최단 경로 수정
                    // 탐색으로 얻은 특정 정점까지의 새 경로가 기존 경로보다 짧다면 반영한다.
                    if (dist[nextN][nextM] > nextCount) {
                        dist[nextN][nextM] = nextCount;
                        que.offer(new Point(nextM, nextN, nextCount));
                    }
                }
            }
        }
    }
}

//TODO 확인할 것 (3): 우선순위 큐의 요소
// Comparable을 구현한 클래스의 객체 사용
class Point implements Comparable<Point> {
    int m;
    int n;
    int count;

    public Point(int m, int n, int count) {
        this.m = m;
        this.n = n;
        this.count = count;
    }

    @Override
    public int compareTo(Point o) {
        return count - o.count;
    }
}
