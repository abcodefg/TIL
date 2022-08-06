package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//#11052 카드 구매하기 풀이
/*순서대로 1개, 2개, ... N개가 든 카드팩의 가격이 주어질 때,
카드 N개를 구매하는 데 지불할 수 있는 금액의 최댓값을 구하는 문제*/
public class DP_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 카드 i개 구매에 지불할 수 있는 최댓값 dp[i]
        // 우선 i개가 든 카드팩의 가격을 i개를 구매하는 기본 가격으로 지정한다
        for(int i = 1; i < N + 1; i++) {
            dp[i] = Integer.parseInt(st.nextToken());
        }

        // 카드 1개에 지불하는 최대액수는 1개짜리 팩의 가격이므로 배제하고
        // 2~N개의 카드를 살 때 지불하는 최대액수를 탐색한다.
        for(int i = 2; i < N + 1; i++) {
            for(int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i - j] + dp[j], dp[i]);
            }
        }

        System.out.println(dp[N]);
    }
}
