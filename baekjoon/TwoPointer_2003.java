package baekjoon;

import java.util.Scanner;

//#2003 수들의 합 2
/*N개의 자연수로 이루어진 수열의 i번째 수부터 j번째 수까지의 합이 M이 되는 경우의 수를 구하라.*/
public class TwoPointer_2003 {
    //이중 반복문을 통한 합산(O(n^2))이 아닌 투 포인터(O(N))를 활용해야 하는 문제였다.
    //반복문 안에서 조정되는 두 변수의 값에 따라 탐색 혹은 계산의 범위를 조정한다는 점에서
    //이분탐색 알고리즘과 유사하다.
    //참고: https://sohyunwriter.tistory.com/117?category=892942
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;
        int sum = 0;
        int start = 0, end = 0;
        //조건문의 의미를 확인해볼 것!
        while(true) {
            //sum이 M과 같거나 크다는 것은
            //범위가 끝나는 인덱스를 늘렸을 때(자연수를 추가로 더햇을 때)
            //sum이 M이 될 수 없다는 것을 의미하므로,
            //시작점을 옮긴다.
            if(sum >= m) {
                sum -= arr[start++];
            }
            else if(end == n) {
                break;
            }
            else {
                sum += arr[end++];
            }

            if(sum == m) {
                count++;
            }
        }
        System.out.println(count);
    }

}
