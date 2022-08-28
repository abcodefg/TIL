package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// # 7576 토마토 풀이
/* M * N 크기의 상자에 담긴 토마토들을 나타내는 배열을 입력받아
* 토마토가 익는 데 걸리는 최소 일수를 구하는 문제.
*
* 하루가 지나면 익은 토마토의 상하좌우에 인접한 토마토가 익는다.
* 1은 익은 토마토, 0은 익지 않은 토마토, -1은 토마토가 없는 칸을 의미한다.*/
public class Graph_7576 {
    // 하루(한 라운드)마다 인접한 토마토를 찾고 값을 바꾼다는 점에서 bfs가 적절한 접근방법인 것 같다.
    // 재귀를 쓰는 것도 고민해보았지만 토마토가 전부 익지 않을 수 있다는 점에서
    // 재귀를 종료하기 위한 base case를 설정하기가 까다로웠다.
    // 대신 큐를 사용하되, 인접해 있으면서 익지 않은 토마토(0)가 있어야 큐가 채워지게 했다.
    // 더이상 그런 토마토가 없으면 큐가 비워지면서 자동으로 탐색을 종료하고
    // 그때까지 처음 배열을 입력받을 때 센 0의 개수와 큐를 채우면서 센 0의 개수를 비교해
    // 모든 토마토가 익었는지 확인하는 방식을 사용했다.
    // 다만, 일수를 세야 하므로 하루에 익는 토마토들을 묶어주기 위해 내부에 큐를 하나 더 만들었다.
    static int M, N, count, testCount;
    static int[][] arr;
    static int dy[] = {-1, 1, 0, 0};
    static int dx[] = {0, 0, -1, 1};
    static Queue<Queue<int[]>> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        count = 0;
        int zeroCount = 0;
        testCount = 0;
        arr = new int[N][M];
        Queue<int[]> inQue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tomato = Integer.parseInt(st.nextToken());
                if (tomato == 1) {
                    inQue.add(new int[]{i, j});
                } else if (tomato == 0) {
                    zeroCount++;
                }
                arr[i][j] = tomato;
            }
        }
        que.add(inQue);
        bfs();
        if(testCount != zeroCount) {
            System.out.println(-1);
        } else {
            System.out.println(count);
        }
    }

    static void bfs() {
        // 큐가 완전히 빌 때까지 반복
        while(!que.isEmpty()){
            // 큐에서 내부의 큐를 뽑아냄
            // 내부의 큐는 1개의 라운드 구성
            Queue<int[]> inQue = que.poll();
            // 같은 일수에 익는 토마토의 좌표를 모아줄 큐를 생성
            Queue<int[]> newInQue = new LinkedList<>();
            // 내부 큐가 빌 때까지 반복
            while(!inQue.isEmpty()) {
                // 현재 토마토의 좌표를 설정
                int curY = inQue.peek()[0];
                int curX = inQue.peek()[1];
                inQue.poll();

                // 현재 토마토와 인접한 지점들 반복문으로 탐색
                for (int i = 0; i < 4; i++) {
                    int nextY = curY + dy[i];
                    int nextX = curX + dx[i];
                    // 좌표가 상자 안에 있고
                    if (nextY >= 0 && nextX >= 0 && nextY < N && nextX < M) {
                        // 그곳에 익지 않은 토마토가 있다면
                        if(arr[nextY][nextX] == 0) {
                            // 익은 것으로 바꾸고, 내부 큐에 추가하고, 0->1로 바뀐 토마토 개수 카운트
                            arr[nextY][nextX] = 1;
                            newInQue.add(new int[]{nextY, nextX});
                            testCount++;
                        }
                    }
                }
            }
            // 다음 일수에 익는 토마토가 있다면
            if(!newInQue.isEmpty()){
                // 일수를 카운트하고 외부 큐에 추가
                count++;
                que.add(newInQue);
            }
        }
    }
}
