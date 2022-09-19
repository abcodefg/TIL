package baekjoon;

import java.util.Scanner;

//#1451 직사각형으로 나누기
/*한 칸에 한 자리의 숫자(1~9)가 쓰인 N * M 크기의 직사각형이 있다.
* 이를 3개의 작은 직사각형으로 나누려고 한다.
* 단, 하나의 작은 직사각형은 적어도 하나의 숫자를 포함해야 한다.
* 각 작은 직사각형에 포함된 숫자의 합을 구하고, 3개의 합을 곱한 최대값을 구하시오*/
public class BF_1451 {
    //직사각형을 3개의 작은 직사각형으로 나누는 경우를 6개 유형으로 나누고
    //각각에 대해 작은 직사각형들의 합의 곱을 구해 그 중 최대값을 기록한다.
    static int[][] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        arr = new int[N][M];

        for(int i = 0; i < N; i++) {
            String str = sc.nextLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        // TODO 확인할 것 (1): 리팩토링. 작은 직사각형의 합 미리 구하기
        // (0,0)부터 (i,j)까지 포함하는 작은 직사각형의 합을 구해 저장한다.
        // (0<=i<=N-1) && (0<=b<=M-1)일 때, (0,0)부터 (i,j)까지 포함하는 작은 직사각형의 합 이차원 배열에 저장한다..
        // 이를 sum 메서드에서 활용해 작은 직사각형의 합을 빠르게 구한다.

        // TODO 확인할 것 (2): 출력값의 최대 크기 고려해 자료형 설정
        // N과 M이 50보다 작거나 같고, 한 칸에 들어갈 수 있는 최대값은 9이다.
        // 총 모든 칸에 9가 입력된 2500칸의 직사각형을 800, 800, 900칸의 작은 직사각형으로 나눈다고 가정할 때
        // 7200 * 7200 * 8100은 21억 4700만...(int 자료형의 표현 범위)을 초과한다.
        // 따라서 곱한 값의 자료형은 long.
        long multiplied;
        long max = 0;

        //세로로 3개 나누는 경우
        for(int i = 1; i <= N - 2; i++) {
            for(int j = 2; j <= N - 1; j++) {
                multiplied =
                        sum(0, 0, i - 1, M - 1) *
                        sum(i, 0, j - 1, M - 1) *
                        sum(j, 0, N - 1, M - 1);
                max = Math.max(max, multiplied);
            }
        }

        //가로로 3개 나누는 경우
        for(int i = 1; i <= M - 2; i++) {
            for(int j = 2; j <= M - 1; j++) {
                multiplied =
                        sum(0, 0, N - 1, i - 1) *
                        sum(0, i, N - 1, j - 1) *
                        sum(0, j, N - 1, M - 1);
                max = Math.max(max, multiplied);
            }
        }

        //세로로 1개, 그 아래 혹은 위에 가로로 2개 나누는 경우
        for(int i = 0; i <= N - 2; i++) {
            long firstSquare = sum(0, 0, i, M - 1);
            long lastSquare = sum(N - 1 - i, 0, N - 1, M - 1);
            for(int j = 1; j <= M - 1; j++) {
                multiplied =
                        firstSquare *
                        sum(i + 1, 0, N - 1, j - 1) *
                        sum(i + 1, j, N - 1, M - 1);
                max = Math.max(max, multiplied);

                multiplied =
                        sum(0, 0, (N - 1 - i) - 1, j - 1) *
                        sum(0, j, (N - 1 - i) - 1, M - 1) *
                        lastSquare;
                max = Math.max(max, multiplied);
            }
        }

        //가로로 1개, 그 왼쪽 혹은 오른쪽에 세로로 2개 나누는 경우
        for(int i = 0; i <= M - 2; i++) {
            long firstSquare = sum(0, 0, N - 1, i);
            long lastSquare = sum(0, M - 1 - i, N - 1, M - 1);
            for(int j = 1; j <= N - 1; j++) {
                multiplied =
                        firstSquare *
                        sum(0, i + 1, j - 1, M - 1) *
                        sum(j, i + 1, N - 1, M - 1);
                max = Math.max(max, multiplied);

                multiplied =
                        sum(0, 0, j - 1, (M - 1 - i) - 1) *
                        sum(j, 0, N - 1, (M - 1 - i) - 1) *
                        lastSquare;
                max = Math.max(max, multiplied);
            }
        }

        System.out.println(max);

    }

    static long sum(int startN, int startM, int endN, int endM) {
        long sum = 0;
        for(int i = startN; i <= endN; i++) {
            for(int j = startM; j <= endM; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
}
