package baekjoon;

import java.util.Scanner;

//#1806 부분합
/*10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
* 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상 되는 것 중,
* 가장 짧은 것의 길이를 구하는 프로그램을 구하시오.*/
public class TwoPointer_1806 {
    //'2003번 수들의 합 2' 풀이의 응용버전이다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int start = 0, end = 0;
        int sum = 0;
        int shortest = 100000;
        while(true) {
            if (sum >= s) {
                sum -= arr[start++];
            }
            else if (end == n) {
                break;
            }
            else {
                sum += arr[end++];
            }

            if (sum >= s) {
                shortest = Math.min(shortest, end - start);
            }
        }

        shortest = (shortest == 100000) ? 0 : shortest;
        System.out.println(shortest);
    }
}
