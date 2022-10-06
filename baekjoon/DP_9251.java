package baekjoon;

import java.util.Scanner;

//#9251 LCS(Longest Common Subsequence, 최장 공통 부분 수열(혹은 문자열))
/*두 문자열이 주어졌을 때 공통의 부분 문자열 중 가장 긴 것(LCS)의 길이를 구하는 문제.
* ex) 'ACAYKP'와 'CAPCAK'의 LCS는 'ACAK'*/
public class DP_9251 {
    /**
     * "DP 문제 풀이의 핵심은 보다 작은 단위에서 정의될 수 있는 sub-problem 을 찾는 것이다."
     */
    //BOTTOM-UP 방식(TABULATION)으로 풀었다.
    //ABCD와 ACAA를 비교하는 경우를 살펴보자.
    //첫 번째 문자가 'A'로 일치하므로 ('ABCD'와 'ACAA'의 LCS) 는 ('A' + 'BCD'와 'CAA'의 LCS) 와 같다.
    //두 번째 문자는 각각 'B'와 'C'로 다르다. 이 경우, ('BCD'와 'CAA'의 LCS) 는
        //(BCD'와 'AA'의 LCS)와 ('CD'와 'CAA'의 LCS) 중 더 큰 것이다.
        //일치하지 않는 두 문자 중 하나를 제외해도 결과에는 영향이 없기 때문이다.
    //이제 세로와 가로 인덱스에 두 문자열의 문자가 차례대로 대응되는 이차원 배열을 생각하고 문제를 풀어보자.
    static char[] str1;
    static char[] str2;
    static int[][] tabulation;
    static Integer[][] memoization;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str1 = sc.nextLine().toCharArray();
        str2 = sc.nextLine().toCharArray();
        tabulation = new int[str1.length + 1][str2.length + 1];
        memoization = new Integer[str1.length + 1][str2.length + 1];

        //TODO 확인할 것: 2차원 TABULATION
        for(int i = str1.length - 1; i >= 0; i--) {
            for(int j = str2.length - 1; j >= 0; j--) {
                if(str1[i] == str2[j]) {
                    tabulation[i][j] = tabulation[i + 1][j + 1] + 1;
                } else {
                    tabulation[i][j] = Math.max(tabulation[i + 1][j], tabulation[i][j + 1]);
                }
            }
        }

        System.out.println("Tabulation(BOTTOM-UP) answer: " + tabulation[0][0]);

        System.out.println("Memoization(TOP-DOWN) answer: " + dp(0,0));
    }

    static int dp(int i, int j) {
        if(i == str1.length || j == str2.length) {
            return 0;
        }
        if(memoization[i][j] != null) return memoization[i][j];
        int answer;
        if(str1[i] == str2[j]) {
            answer = 1 + dp(i + 1, j + 1);
        } else {
            answer = Math.max(dp(i + 1, j), dp(i, j + 1));
        }
        memoization[i][j] = answer;
        return memoization[i][j];
    }
}
