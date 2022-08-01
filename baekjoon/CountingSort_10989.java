package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// #10989 수 정렬하기 3
/*크기가 1 이상 10000 이하인 무작위 정수의 배열을 입력받아 정렬해 반환하는 문제
* Counting Sort: 1. 정렬하고자 하는 배열의 요소 중 최대값 + 1의 카운팅 배열을 만들어 각 숫자에 해당하는 인덱스에 갯수를 저장
*                2. 카운팅 배열을 기반으로 정렬된 배열을 생성*/
public class CountingSort_10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 카운팅 배열 생성
        int[] count = new int[10001];
        // 숫자별 카운트 기록
        for(int i = 0; i < N; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }
        br.close();
        // 정렬된 배열 생성
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < 10001; i++) {
            while(count[i] > 0) {
                sb.append(i).append('\n');
                count[i]--;
            }
        }
        System.out.println(sb);
    }
}
