package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//#2011 암호코드 풀이
/*A를 1, B를 2, .... Z는 26으로 암호화한다. 어떤 암호가 주어졌을 때 글자로 바꾸는 경우의 수를 구하는 문제*/
public class DP_2011 {
    // 예외 처리가 까다로웠던 문제. 규칙을 차근차근 적용하는 것이 중요.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        if(str.charAt(0) == '0') {
            System.out.println("0");
            return;
        }

        long[] dp = new long[str.length() + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2; i <= str.length(); i++) {
            char curr = str.charAt(i - 1);
            char prev = str.charAt(i - 2);
            if (curr == '0') {
                if(prev == '1' || prev == '2')
                    dp[i] = dp[i - 2] % 1000000;
                else break;
            }
            else {
                if(prev == '0')
                    dp[i] = dp[i - 1] % 1000000;
                else {
                    int temp = (prev - '0') * 10 + (curr - '0');
                    if(1 <= temp && temp <= 26)
                        dp[i] = (dp[i - 2] + dp[i - 1]) % 1000000;
                    else dp[i] = dp[i - 1] % 1000000;
                }
            }
        }
        System.out.println(dp[str.length()] % 1000000);
    }
}
