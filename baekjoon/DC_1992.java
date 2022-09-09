package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//#1992 쿼드트리
/*문제 내용은 사이트 참고*/
public class DC_1992 {
    // 1780 종이의 개수 문제와 기본적인 접근방법이 동일하다.
    // 다만 반환값을 구성하는 방법이 다른데,
    // 재귀 호출의 흐름을 가볍게 이해하는 데 좋을 것 같다.
    static char[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        quadTree(0, 0, N);
        System.out.println(sb);
    }
    static void quadTree(int xStart, int yStart, int length) {
        char num = arr[xStart][yStart];

        outer:
        for(int i = xStart; i < xStart + length; i++) {
            for(int j = yStart; j < yStart + length; j++) {
                if(arr[i][j] != num) {
                    sb.append("(");
                    break outer;
                }
            }
            if (i == xStart + length - 1) {
                sb.append(num);
                return;
            }
        }

        for(int i = xStart; i <= xStart + length / 2; i += length / 2) {
            for(int j = yStart; j <=yStart + length / 2; j += length / 2) {
                quadTree(i, j, length / 2);
            }
        }
        sb.append(")");
    }
}
