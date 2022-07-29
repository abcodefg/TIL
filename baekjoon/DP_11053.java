package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 백준 #11053 가장 긴 증가하는 부분수열(LIS) 풀이
public class DP_11053 {
    /*배열의 길이와 양의 정수 배열을 입력받아 최장 증가 부분수열을 구하라. 다이나믹 프로그래밍 문제의 대표적인 유형이다.
    * 재귀와 Memoization을 활용한 TOP-DOWN방식이 아닌 Tabulation을 통한 BOTTOM-UP방식으로 풀어보았다.
    * 배열의 규칙을 찾아내고 첫번째 인덱스에서 시작해 상향식으로 각 인덱스에서 끝나는 LIS를 채워나가는 것이 기본적인 접근방법이다*/
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 입력받은 배열과 동일한 크기의 배열을 새로 만들어
        // 해당 인덱스의 숫자를 끝으로 하는 LIS의 길이를 입력할 것이다.
        // 이때, 각 숫자의 LIS는 적어도 1(자기 자신) 이상이므로 모든 인덱스에 1을 입력한다.
        int[] LIS = new int[N];
        for(int i = 0; i < N; i++) {
            LIS[i] = 1;
        }

        // 1번 인덱스부터 끝 인덱스까지 순회한다
        for(int i = 1; i < N; i++) {
            // 해당 인덱스의 이전 인덱스를 순회하며
            for(int j = 0; j < i; j++) {
                // 이전 인덱스의 숫자가 더 작아 증가수열이 성립한다면
                if(arr[i] > arr[j])
                    // 그 숫자에서 끝나는 LIS에 1을 더한 값을 본 인덱스 숫자의 LIS로 입력한다
                    // 이때, 반복문을 순회하면서 이전의 다른 인덱스에 보다 큰 LIS가 있다면 바꾼다
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
            }
        }
        // 배열의 각 요소에서 끝나는 LIS중 가장 긴 값을 출력한다
        int max = 0;
        for(int i = 0; i < N; i++) {
            max = Math.max(max, LIS[i]);
        }

        System.out.println(max);
    }
}