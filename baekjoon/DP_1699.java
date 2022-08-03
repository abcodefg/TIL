package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 #1699 제곱합 풀이
/* 어떤 수를 제곱수의 합으로 나타낼 때 제곱수의 최소 개수를 구하는 문제
*  재귀를 사용해서도 풀었으나, 반복문으로 푸는 게 훨씬 효율적인 것 같다 */

public class DP_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j*j <= i; j++) {
                if(dp[i] > 1 + dp[i - j*j])
                    dp[i] = 1 + dp[i - j*j];
            }
        }
        System.out.println(dp[N]);
    }
}
