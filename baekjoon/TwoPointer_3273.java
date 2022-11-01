package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

//#3273 두 수의 합
/*n개의 서로 다른 야으이 정수로 이루어진 수열이 있다.
* 자연수 x가 주어졌을 때, a1 + a2 = x를 만족하는 (a1,a2) 쌍의 개수를 구하라.*/
public class TwoPointer_3273 {
    /**
     * 단순한 접근법은 브루트포스로 모든 두 수의 합을 구하는 것이다.
     * 이 경우, 시간복잡도가 O(n^2)이므로 효율적인 방법이 필요하다.
     *
     * 두 포인터는 정렬된 배열에서 요소에 대한 조건(ex_두 요소의 합)에 따라
     * 경우의 수를 셀 때 유용한 알고리즘이다.
     * 시간복잡도는 O(N)이다.
     *
     * 단, 이 풀이의 경우 정렬을 위해 DualPivotQuickSort 방식의 Arrays.sort() 메서드를 사용했으므로
     * 시간복잡도는 평균 O(n log n), 최악 O(n^2)이라고 보는 게 맞다.
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int x = sc.nextInt();

        int count = 0;
        int p1 = 0;
        int p2 = n - 1;
        while (p1 < p2) {
            int sum = arr[p1] + arr[p2];
            if (sum == x)
                count++;
            if (sum <= x)
                p1++;
            else
                p2--;
        }
        System.out.println(count);
    }
}
