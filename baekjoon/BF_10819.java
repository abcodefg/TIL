package baekjoon;

import java.util.Scanner;

//#10819 차이를 최대로
/*N개의 정수로 이루어진 배열 A가 주어진다.
*배열에 들어있는 정수의 순서를 적절히 바꿔 다음 식의 최대값을 구하라.
* |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|*/
public class BF_10819 {
    //케이스가 하나이고 배열의 길이가 그렇게 길지 않아(3 ≤ N ≤ 8)
    //안심하고 브루트포스로 접근했다.
    //N개의 정수로 이루어진 배열의 순서를 바꾸는 건
    //N개의 숫자에서 N개를 뽑는 순열 알고리즘을 활용했다.
    static int max = 0;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        permutation(arr, 0, N);
        System.out.println(max);
    }
    //배열을 섞는 메서드(순열)
    //n개 중 r개를 뽑는 순열(nPr)을 swap을 이용해 나타낸 알고리즘을 응용
    //이 문제에선 r==n이므로 매개변수 r은 제거했다.
    static void permutation(int[] arr, int depth, int n) {
        if(depth == n) {
            result(arr);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    //완성된 배열에 대해 값을 구하는 메서드
    static void result(int[] arr) {
        int sum = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            sum += Math.abs(arr[i] - arr[i + 1]);
        }
        max = Math.max(max, sum);
    }
}
