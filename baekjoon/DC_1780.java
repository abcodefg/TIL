package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #1780 종이의 개수
/*(N * N) 크기의 행렬로 표현되는 종이의 각 칸에 -1, 0, 1 중 하나가 저장되어 있다.
* 종이가 모두 같은 수로 되어 있다면 그대로 쓰고, 아닌 경우에는 종이를 9개로 자른다.
* 이후 앞의 과정을 반복해서 -1, 0, 1로만 채워진 종이의 개수를 구하는 문제*/
public class DC_1780 {
    // 전형적인 Divide and Conquer 문제이다.
    // 같은 숫자로 채워져있는 종이가 될 때까지 나누고(divide),
    // 종이에 적힌 숫자를 센다(conquer).
    static int[][] arr;
    static int[] paperNum = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paperCut(0, 0, N);
        for(int i = 0; i < 3; i++) {
            System.out.println(paperNum[i]);
        }
    }
    static void paperCut(int xStart, int yStart, int length) {

        // 종이가 같은 숫자로만 채워져있는지 확인한다.
        int num = arr[xStart][yStart];
        outer:
        for(int i = xStart; i < xStart + length; i++) {
            for(int j = yStart; j < yStart + length; j++) {
                // 같은 숫자로만 채워져있지 않다면 반복문을 종료하고 다음 반복문을 실행한다.
                if(arr[i][j] != num) {
                    break outer;
                }
            }
            // 같은 숫자로만 채워져있다면 종이에 적힌 숫자를 카운트하고 메서드를 종료한다.
            if(i == xStart + length - 1) {
                paperNum[num + 1]++;
                return;
            }
        }
        // 같은 숫자로만 채워져 있지 않은 종이를 9등분하여 각각을 재귀 호출한다.
        for(int i = xStart; i <= xStart + 2 * (length / 3); i+= length / 3) {
            for(int j = yStart; j <= yStart + 2 * (length / 3); j+= length / 3) {
                paperCut(i, j, length / 3);
            }
        }
    }
}
