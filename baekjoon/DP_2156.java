package baekjoon;

import java.util.Scanner;

public class DP_2156 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N + 1];
        long[] dp = new long[N + 1];
        for (int i = 1; i < N + 1; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0] = 0;
        dp[1] = arr[1];
        if(N >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        for (int i = 3; i < N + 1; i++) { // 재귀를 활용해서 풀 수도 있지만 이번엔 반복문으로 풀어보았다.
           long temp = arr[i] + Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]);
            dp[i] = Math.max(dp[i - 1], temp);
        }

        System.out.println(dp[N]);
    }
}
