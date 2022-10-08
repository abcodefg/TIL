package baekjoon;

import java.util.Scanner;

//#1182 부분수열의 합
/*정수 N과 S와 N개의 정수로 이루어진 수열이 주어진다.
* 이 수열의 크기가 양수인 부분수열 중에서
* 원소를 모두 더한 값이 S가 되는 경우의 수를 구하시오.*/
public class Backtracking_1182 {
    static int N, S, count;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        count = 0;
        dfs(0, 0, 0);
        System.out.println(count);
    }

    static void dfs(int idx, int sum, int size) {
        if(idx == N) {
            //요소의 합이 S이고 크기가 양수인 부분수열인 경우 카운트
            if(sum == S && size != 0) {
                count++;
            }
            return;
        }
        //백트래킹
        if((N - idx) * 100000 < (S - sum) ||
                (N - idx) * -100000 > S + sum) return;

        dfs(idx + 1, sum + arr[idx], size + 1);
        dfs(idx + 1, sum, size);
    }
}
