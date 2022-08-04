package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 백준 #2133 타일 채우기 풀이

public class DP_2133 {
    /*3*N 크기의 타일을 2*1, 1*2 크기의 타일로 채우는 경우의 수를 구하는 문제
    * N이 홀수인 경우 타일의 넓이인 3*N 역시 홀수이므로 넓이가 짝수인 타일들로 채워지는 경우의 수는 0이다.*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];

        // *점화식의 시그마에 해당하는 부분을 한 번의 반복문으로 같이 형성해준다.*
        int prev = 0;
        dp[0] = 0;
        dp[2] = 3;
        for(int i = 4; i <= N; i += 2) {
            dp[i] += dp[i - 2] * 3 + 2 + prev * 2;
            prev += dp[i - 2];
        }
        System.out.println(dp[N]);
    }
}
