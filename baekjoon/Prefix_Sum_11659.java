package baekjoon;

import java.util.Scanner;

//#11659 구간 합 구하기 4
/*수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오*/

public class Prefix_Sum_11659 {
    /**
     * 최근에 내 풀이방식 외에 구간 합(또는 누적 합)으로도 접근할 수 있는 문제들을 몇 개 발견했다.
     * 기초적인 접근방법을 정리하고자 해당 문제풀이를 기록한다.
     * 반복문을 사용하여 구간 합을 구하는 알고리즘의 시간 복잡도는 O(N)인 반면,
     * 구간 합 알고리즘의 시간 복잡도는 O(1)이다.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] prefSum = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            prefSum[i] = prefSum[i - 1] + sc.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < M; k++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            sb.append(prefSum[j] - prefSum[i - 1]).append('\n');
        }
        System.out.println(sb);
    }
}
