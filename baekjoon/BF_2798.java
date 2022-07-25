package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 #2798 블랙잭 풀이
public class BF_2798 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 처음에 생각한 방식
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {                       // 단순하게 세 숫자의 인덱스를 겹치지 않게 하려 했으나
                    for (int k = 0; k < N; k++) {   // 불필요하게 연산이 늘어나고, 셈이 중복된다는 문제가 생겼다
                        if(i != k && j != k) {      // (정답이 나오긴 했다.)
                            int sum = arr[i] + arr[j] + arr[k];
                            if(sum <= M)
                                max = Math.max(sum, max);
                        }
                    }
                }
            }
        }
        // 개선한 방식
        for (int i = 0; i < N - 2; i++) {           // 뽑은 세 숫자 간의 순서를 유지하는 형태로
            for (int j = i + 1; j < N - 1; j++) {   // 반복문의 순회 범위를 좁혔다
                for (int k = j + 1; k < N; k++) {   // 수행시간과 메모리가 줄어들었다
                    int sum = arr[i] + arr[j] + arr[k];
                    if(sum == M) {
                        max = sum;
                        break;
                    }
                    if(sum <= M && sum >= max)
                        max = sum;
                }
            }
        }
        System.out.println(max);
    }
}
