package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// #2981 검문 풀이
/*풀이는 추후에 올릴 것*/
public class math_NT_2981 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int GCD = arr[1] - arr[0];

        for(int i = 1; i < N - 1; i++) {
            GCD = GCD(GCD, arr[i + 1] - arr[i]);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= GCD; i++) {
            if (GCD % i == 0) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb);

    }

    static int GCD(int x, int y) {
        int a = Math.max(x, y);
        int b = Math.min(x, y);

        while(b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }
}
