package baekjoon;

import java.io.IOException;
import java.util.Scanner;

// #1654 랜선 자르기
/*K개의 랜선을 잘라 동일한 길이 x의 랜선 N개를 만들려고 한다. 이때 x의 최대값을 구하는 문제.*/
public class BinarySearch_1654 {
    // 처음 풀이에서 예외 케이스를 간과해서 틀렸다. 틀린 이유를 찾느라 한참을 헤맸는데 결국 검색의 힘을 빌리긴 했다.
    // 덕분에 모호하게 알고 있었던 Upper Bound, Lower Bound가 활용되는 방식을 정리할 수 있었다.
    // 개념을 정리하고 나니 변수 설정도 근거를 갖고 명확하게 할 수 있었다.
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        long high = 0;

        int[] arr = new int[K];
        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextInt();
            high = Math.max(high, arr[i]);
        }

        // TODO 확인할 것 (1): 예외 케이스 주의
        //  랜선의 길이가 {1, 1}로 주어지면 mid가 0이 나온다. 반복문에서 0으로 나누게 되므로 1을 더해준다.
        //  1을 더해주어 예외 발생을 방지할 수 있다.
        // high는 가장 긴 랜선의 길이
        high += 1;
        long low = 0;

        while (low < high) {
            long mid = (low + high) / 2;
            // 나눈 랜선의 수가 목표보다 많거나 같다면
            long count = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                count += arr[i] / mid;
                if(count >= N) {
                    break;
                }
            }
            // 나눈 랜선의 수가 목표보다 많거나 같다면
            if(count >= N) {
                // 하나당 길이가 긴 쪽으로 범위를 좁힌다.
                low = mid + 1;
                // 나눈 랜선의 수가 목표보다 적다면
            } else {
                // 하나당 길이가 짧은 쪽으로 범위를 좁힌다.
                high = mid;
            }
        }
        // TODO 확인할 것 (2): 목표값을 처음 초과한 값(Upper bound)을 반환하므로 1을 뺀다.
        System.out.println(low - 1);
    }
}