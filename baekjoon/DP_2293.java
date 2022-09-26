package baekjoon;

import java.util.Scanner;

//#2293 동전 1
/*풀이를 참고했다.
* 가장 친절하고 상세한 해설 링크 https://www.youtube.com/watch?v=Mjy4hd2xgrs
* 위의 해설 링크를 먼저 달아놓은 이유가 있다.
* 온라인 상의 다른 풀이들은 (1) 처음부터 당연하다는 듯이 문제의 카테고리를 dp로 분류하고
* (2) dp의 전형적인 풀이방식에 문제를 끼워맞춘다.
* 그 결과, 중간과정이 생략된 해설로 인해 문제를 이해하는 것이 매우 까다로워진다.
*
* 반면, 링크의 풀이는 가장 기초적이고 직관적인 경우의 수 세기에서 시작해서
* 비효율적인 부분을 제거하는 방식으로 풀이를 '발전'시켜나간다.
* 그 결론은 다른 풀이들처럼 다이나믹 프로그래밍이지만, 이 경우엔
* 문제에 대한 이해와 풀이의 방식이 논리적으로 연결되어 있고 dp는 알고리즘을 '최적화'한 결과물일 뿐이다.
*
* 이 풀이를 보고 내가 기존에 알고리즘 문제를 풀어오던 방식을 반성하게 됐다.
* 문제에 대한 이해와 논리적인 근거가 미흡한 기계적인 풀이에만 급급했다는 걸 깨달았기 때문이다.
* 이 문제가 마음가짐과 접근 방식을 재정비하는 계기가 되었으면 좋겠다.
* */
public class DP_2293 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] coins = new int[n];
        for(int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        //dp[x] = x원을 지불하는 경우의 수
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int j = coin; j <= k; j++) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.println(dp[k]);
    }
}
