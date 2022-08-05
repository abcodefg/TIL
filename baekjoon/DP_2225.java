package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #2225 합분해 풀이
/* 양의 정수 N과 K가 주어졌을 때, 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 문제
* 덧셈의 순서가 바뀐 경우는 다른 경우로 센다. */
public class DP_2225 {
    // 규칙을 찾고 나니 코드로 옮기는 건 수월했다.
    // 4(N)를 숫자 3(K)개의 합으로 나타낸다면 이는 (x + 0), (x + 1), (x + 2), (x + 3), (x + 4) 중 하나의 형태를 띌 것이다.
    // 그리고 이때 각 경우의 수는 x를 숫자 2개의 합으로 나타내는 경우의 수(f(x, 2))와 동일할 것이므로
    // f(4, 3)은 f(4, 2) + f(3, 2) + f(2, 2) + f(1, 2) + f(0, 2)와 같다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N + 1][K + 1];

        for(int i = 0; i < N + 1; i++) {
            dp[i][1] = 1;
        }
        for(int i = 1; i < K + 1; i++) {
            dp[0][i] = 1;
        }
        for(int i = 2; i < K + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                dp[j][i] = ((dp[j - 1][i] + dp[j][i - 1]) % 1000000000);
            }
        }
        System.out.println(dp[N][K]);
    }
}
