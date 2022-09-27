package baekjoon;

import java.util.Scanner;

//#2805 나무자르기
/*한 줄로 서있는 나무들을 벌목하려고 한다.
* 목재절단기는 지정한 높이를 가로지르며 나무들에서 그보다 높은 부분만 잘라낸다.
* 나무를 필요한 만큼만 자르고자 할 때 설정할 높이의 최대값을 구하라.*/
public class BinarySearch_2805 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long high = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            high = Math.max(high, arr[i]);
        }
        long low = 0;
        while (low < high) {
            long mid = (low + high) / 2;
            long sum = 0;
            //mid가 지정한 높이일 때 잘라낸 목재의 길이를 구한다.
            for (int i = 0; i < N; i++) {
                if (mid < arr[i]) {
                    sum += (arr[i] - mid);
                }
            }
            //잘라낸 목재의 길이가 목표치보다 작다면 지정할 높이의 범위를 낮은 쪽으로 좁힌다.
            if (sum < M) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }
}
