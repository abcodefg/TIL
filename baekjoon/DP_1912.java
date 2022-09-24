package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//#1912 연속합
/*N개의 정수로 이루어진 임의의 수열이 주어진다.
* 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 가장 큰 합을 구하라.*/
public class DP_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] conSum = new int[N];
        int max = -1000;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        conSum[0] = arr[0];

        for(int i = 1; i < arr.length; i++) {
            conSum[i] = Math.max(arr[i], conSum[i - 1] + arr[i]);
            max = Math.max(max, conSum[i]);
        }

        System.out.println(max);
    }
}
