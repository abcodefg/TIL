package baekjoon;

import java.util.Scanner;

//#1107 리모컨
/*TV 리모컨의 버튼은 0~9까지의 숫자와 +, -(채널 1씩 이동)가 있다.
* 이동하려고 하는 채널 N과 리모컨의 고장난 숫자 버튼이 주어졌을 때,
* N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지 구하시오.*/
public class BF_1107 {
    // 처음엔 고장나지 않은 버튼의 숫자로 이루어진 N과 가장 가까운 숫자를 구하려고 했다.
    // 그러나 이렇게 접근하니 시간을 오래 들여 구현하고 나서도 무수한 예외 케이스의 늪에 빠졌다.
    // 결국 다른 분의 풀이를 참고했다. https://codeung.tistory.com/208
    // 단순한 브루트포스라고 하기에는 구현방식이 간단명료하고 배울 점이 많아 기록한다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        boolean[] isBroken = new boolean[10];
        for(int i = 0; i < M; i++) {
            isBroken[sc.nextInt()] = true;
        }

        // +, - 버튼만으로 이동할 때 횟수
        // 이것보다는 작아야 값을 교체한다.
        int result = Math.abs(N - 100);
        // 이동하려는 채널 N은 최고 50만이지만 전체 채널은 무한대이다.
        // 경우에 따라 50만보다 큰 채널로 숫자버튼을 눌러 이동하고 -를 반복해서 누르는 게
        // 횟수를 최소화하는 경우일 수 있으므로 100만까지 확인한다.
        // (사실, 시작점이 100이므로 범위를 더 좁힐 수 있긴 하다)
        for(int i = 0; i <= 1000000; i++) {
            String num = String.valueOf(i);
            // TODO 확인할 것: boolean 타입 변수 통한 조건 충족여부 기록
            //  적어놓고 보면 간단한데 이상하게 안 떠올랐다.
            boolean check = true;
            for(int j = 0; j < num.length(); j++) {
                if(isBroken[num.charAt(j) - '0']) {
                    check = false;
                    break;
                }
            }
            //고장나지 않은 버튼의 숫자로 이루어져 있다면
            //숫자 버튼과 +/- 버튼으로 이동하는 횟수를 구하고 최솟값을 갱신한다.
            if(check) {
                int count = num.length() + Math.abs(i - N);
                result = Math.min(result, count);
            }
        }
        System.out.println(result);
    }
}
