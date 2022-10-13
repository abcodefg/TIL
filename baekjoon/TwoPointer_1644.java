package baekjoon;

import java.util.Scanner;

//#1644 소수의 연속합
/**/
public class TwoPointer_1644 {
    //두 포인터를 사용할 수 있다는 걸 알아채는 게 관건이었다.
    //문제에 '특정 수의 배열', '연속합' 등의 요소가 있다면 두 포인터를 활용하는 풀이를 고려하자.
    static int N;
    static boolean[] isNotPrime;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        //TODO 확인할 것 (1): 에라스토테네스의 체
        isNotPrime = new boolean[N + 1];
        isNotPrime[0] = isNotPrime[1] = true;
        for(int i = 2; i <= Math.sqrt(N); i++) {
            if(isNotPrime[i] == true) {
                continue;
            }

            for(int j = i * i; j < isNotPrime.length; j += i) {
                isNotPrime[j] = true;
            }
        }

        //TODO 확인할 것 (2): 소수를 탐색하는 두 포인터
        // 탐색 대상이 인덱스가 아닌 '소수'이므로 2에서 시작하며,
        // 수를 변경할 때 해당 수보다 큰 소수 중 가장 작은 수로 변경한다.
        // sum은 (start == end)일 때 start 이며,
        // (start != end)일 때 크기가 start <= x < end 인 소수들의 합이다.
        int start = 2, end = 2;
        int sum = 0, count = 0;
        while(true) {
            if(sum >= N) {
                sum -= start;
                start = nextPrimeOf(start);
            }
            else if (end == N + 1) {
                break;
            }
            else {
                sum += end;
                end = nextPrimeOf(end);
            }

            if(sum == N) {
                count++;
            }
        }

        System.out.println(count);
    }

    static int nextPrimeOf(int num) {
        num = num + 1;
        //N + 1에 도달했다면 이를 그대로 반환한다.
        while(num != N + 1 && isNotPrime[num]) {
            num++;
        }
        return num;
    }
}
