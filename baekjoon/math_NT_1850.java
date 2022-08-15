package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// #1850 최대공약수 풀이
/*모든 자리가 1로만 이루어진 두 자연수 A와 B가 주어졌을 때, A와 B의 최대공약수를 구하는 문제
* A와 B를 이루는 1의 개수를 입력받는다.*/
public class math_NT_1850 {
    // 1로만 이루어진 수 X와 Y가 있고(X >= Y), 각각을 구성하는 1의 개수는 x, y라고 하자.
    // 이 때, X가 Y로 나누어떨어지려면 x는 y로 나누어떨어져야 함을 알 수 있다.
    // A를 이루는 1의 개수와 B를 이루는 1의 개수의 최대공약수만큼의 1로 구성된 X가
    // A와 B의 최대공약수일 것이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long max = Math.max(A, B);
        long min = Math.min(A, B);

        // 유클리드 호제법을 이용해 입력받은 두 수의 최대공약수를 구한다.
            // E(x, y) = E(y, x % y) = ... = E(N, 0)일 때
            // N은 x와 y의 최대공약수이다.
        while(max % min != 0) {
            long temp = max;
            max = min;
            min = temp % min;
        }

        // 구한 최대공약수만큼 1을 연결하여 출력한다.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < min; i++) {
            sb.append("1");
        }
        System.out.println(sb);
    }
}
