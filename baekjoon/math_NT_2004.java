package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//#2004 조합 0의 개수
/*nCm의 끝자리 0의 개수를 구하라.*/
public class math_NT_2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        int count5 = five_power(n) - five_power(m) - five_power(n - m);
        int count2 = two_power(n) - two_power(m) - two_power(n - m);

        System.out.println(Math.min(count5, count2));
    }

    static int five_power(long x) {
        int count = 0;
        while(x >= 5) {
            count += x / 5;
            x /= 5;
        }
        return count;
    }

    static int two_power(long x) {
        int count = 0;
        while(x >= 2) {
            count += x / 2;
            x /= 2;
        }
        return count;
    }
}
