package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 #9461 파도반 수열 풀이
public class DP_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 오버플로우가 발생할 수 있음을 유의하자
        long[] dp = new long[101];
        dp[1] = 1L;
        dp[2] = 1L;
        dp[3] = 1L;
        dp[4] = 2L;

        for(int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            for(int j = 5; j < N + 1; j++) {
                dp[j] = dp[j - 1] + dp[j - 5];
            }
            System.out.println(dp[N]);
        }
    }
}
