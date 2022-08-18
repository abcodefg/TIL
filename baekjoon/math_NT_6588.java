package baekjoon;

import java.io.*;

// #6588 골드바흐의 추측 풀이
/*6 이상 1000000 이하의 모든 짝수 n을 두 홀수 소수, a와 b의 합으로 나타내는 문제
* 이때, a가 가장 작은 경우 하나만을 출력한다.
* 만약 소수의 합으로 나타내는 것이 불가능하다면 'Goldbach's conjecture is wrong'을 출력한다.*/
public class math_NT_6588 {
    // 감히 내가 골드바흐의 추측을 컴퓨터 시뮬레이션으로나마 검증해볼 수 있었다.
    // 여담이지만, 'Goldbach's conjecture is wrong'은 적어도 100만 이하의 숫자에서는 쓸 일이 없다.
    // 이 문제의 강한 골드바흐의 추측은 아직 반례조차 발견되지 않은 미해결 난제이기 때문이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 에라스토테네스의 체를 활용해 1000000 이하의 소수의 목록을 만든다.
        boolean[] prime = new boolean[1000001];
        for (int i = 2; i < 1000001; i++) {
            prime[i] = true;
        }
        // 어떤 숫자 i가 소수라면, 그보다 큰 i의 배수들은 소수가 아니므로
        // prime 배열에서 해당 인덱스의 값을 false로 바꾸어준다.
        for (int i = 2; i < 1000001; i++) {
            if(prime[i]) {
                for (int j = 2; j <= 1000000 / i; j++) {
                    prime[i * j] = false;
                }
            }
        }

        // 입력받은 숫자 n을 구성하는 a(i)와 b(n - i)가 모두 소수라면
        // StringBuilder에 n = a + b를 추가하고 반복문을 종료한다.
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(n != 0) {
            for (int i = 3; i <= n / 2; i += 2) {
                if(prime[i] && prime[n - i]) {
                    // String.format을 쓰는 방법도 가능하다
                    sb.append(n + " = " + i + " + " + (n - i) + '\n');
                    break;
                } else if (i == n / 2) {
                    sb.append("Goldbach's conjecture is wrong." + '\n');
                }
            }
            n = Integer.parseInt(br.readLine());
        }

        System.out.println(sb);
    }
}
