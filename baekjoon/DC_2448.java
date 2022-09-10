package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//#별 찍기 - 11
/*문제는 사이트 참고 https://www.acmicpc.net/problem/2448*/
public class DC_2448 {
    //(1) 좌표를 잡기 위해 행렬이 필요하다는 것을 깨닫고
    //(2) 재귀호출할 메서드의 매개변수로 어떤 것이 필요한지 알아내는 것이 풀이의 포인트였다.
    //이렇게 기본적인 얼개를 짜고 나니 입력값과 삼각형 각 변 길이의 상관관계,
    //하위 패턴 꼭짓점의 상대적인 좌표 등은 비교적 수월하게 찾을 수 있었다.
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new String[N][2 * N - 1];

        star(N - 1, 0, N);
        //별 찍기가 완료된 행렬을 기준으로 반환값을 만든다.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 2 * N - 1; j++) {
                if(arr[i][j] == null) {
                    sb.append(' ');
                } else {
                    sb.append(arr[i][j]);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);

    }
    //별을 찍을 때 기준이 되는 맨 위 꼭지점의 좌표, 삼각형 변의 길이(= 행의 개수)를 입력한다.
    static void star(int x, int y, int N) {
        if(N == 3) {
            arr[y][x] = "*";
            arr[y + 1][x - 1] = "*";
            arr[y + 1][x + 1] = "*";
            for(int i = x - 2; i <= x + 2; i++) {
                arr[y + 2][i] = "*";
            }
            return;
        }
        //하위패턴을 위, 왼쪽 아래, 오른쪽 아래 3개로 나누어 찍는다.
        star(x, y, N / 2);
        star(x - N / 2, y + N / 2, N / 2);
        star(x + N / 2, y + N / 2, N / 2);
    }
}
