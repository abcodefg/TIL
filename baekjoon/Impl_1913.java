package baekjoon;

import java.util.Scanner;

//#1913 달팽이
/*홀수인 자연수 N이 주어졌을 때 1부터 N^2까지의 자연수를 N*N의 표에 채우는 문제
* 자세한 문제 해설은 사이트 참고*/
public class Impl_1913 {
    static int N, target;
    static int[] targetIdx = new int[2];
    // TODO 확인할 것: 진행 방향 전환용 배열
    static int[][] move = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    static int[][] arr;
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        target = sc.nextInt();
        arr = new int[N][N];
        visited = new boolean[N][N];
        snail(0,0,0,(int)Math.pow(N,2));
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        sb.append(targetIdx[0]).append(" ").append(targetIdx[1]);
        System.out.println(sb);
    }

    static void snail(int x, int y, int idx, int num) {
        //배열에 1까지 채운 후 num=0인 재귀가 호출되면 메서드를 종료한다.
        if(num == 0) {
            return;
        }
        visited[y][x] = true;
        arr[y][x] = num;
        if(num == target) {
            targetIdx[1] = x + 1;
            targetIdx[0] = y + 1;
        }
        //다음 좌표를 설정한다.
        int nextX = x + move[idx][1];
        int nextY = y + move[idx][0];
        //다음 좌표가 범위를 벗어나거나 이미 방문한 좌표라면 좌표를 수정한다.
        if(nextX < 0 || nextY < 0 || nextX >= arr[0].length || nextY >= arr.length ||
                visited[nextY][nextX]) {
            idx = (idx == 3) ? 0 : idx + 1;
            snail(x + move[idx][1], y + move[idx][0], idx, num - 1);
        } else {
            snail(nextX, nextY, idx, num - 1);
        }
    }
}
