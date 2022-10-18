package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

//#1208 부분수열의 합 2
/*N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서
* 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하라.*/
public class MeetInTheMiddle_1208 {
    /**
     * 크기가 N인 배열의 부분수열을 구한다면 시간복잡도가 O(2^N)이다.
     * Meet In The Middle 전략을 통해 O(2^N)에서 O(2^(N/2)*2)로 개선시킬 수 있다.
     * 이를 위해, 주어진 수열을 반으로 나누어 각각에 대해 모든 부분수열 원소의 합을 모아놓은 배열을 구한다.
     * (이때, 부분수열 원소의 합 배열은 '비트연산자와 반복문' 혹은 '재귀 dfs'를 사용해 구할 수 있다.)
     *
     * 이후 두 배열 원소의 합이 0이 되는 경우를 세어주는데 경우의 수를 탐색하는 방법은 크게 두 가지이다.
     * 1. 한 배열만 정렬한 뒤, 정렬된 배열에서 이분탐색 알고리즘을 사용
     * 2. 두 배열을 모두 정렬한 뒤, 두 포인터 알고리즘을 사용
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int S = sc.nextInt();

        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int[] sum1 = new int[1 << N/2];
        int[] sum2 = new int[1 << (N - N/2)];

        //두 개의 부분수열 원소의 합 배열을 만든다.
        //재귀 dfs와 List를 사용하는 방식은 단순하므로, 비트 연산자를 사용하는 방식을 기록하고자 한다.
        //크기가 n인 배열의 부분수열의 가짓수는 배열의 각 원소가 포함되거나 포함되지 않는 2^n가지이다.
        //따라서 부분수열의 합 배열의 인덱스는 이진법으로 0 ~ 11111...(1이 n개)로 나타낼 수 있다.
        for (int i = 0; i < sum1.length; i++)
            for (int j = 0; j < N/2; j++)
                if ((i & (1 << j)) > 0)
                    sum1[i] += arr[j];

        for (int i = 0; i < sum2.length; i++)
            for (int j = 0; j < N - N/2; j++)
                if ((i & (1 << j)) > 0)
                    sum2[i] += arr[N/2 + j];

        Arrays.sort(sum1);
        Arrays.sort(sum2);

        //1. 이분탐색(lower bound와 upper bound)을 활용한 풀이
        long countA = 0;
        for (int leftSum : sum1) {
            int count = upperBound(S - leftSum, sum2) - lowerBound(S - leftSum, sum2);
            countA += count;
        }

        //2. 두 포인터를 활용한 풀이
        long countB = 0;
        int p1 = 0, p2 = sum2.length - 1;
        while (p1 < sum1.length && p2 >= 0) {
            int left = sum1[p1];
            int right = sum2[p2];
            if (left + right > S) {
                p2--;
            } else if (left + right < S) {
                p1++;
            } else {
                long cnt1 = 0, cnt2 = 0;
                while (p1 < sum1.length && sum1[p1] == left) {
                    cnt1++;
                    p1++;
                }
                while (p2 >= 0 && sum2[p2] == right) {
                    cnt2++;
                    p2--;
                }
                countB += cnt1 * cnt2;
            }
        }

        //구하고자 하는 원소의 합이 0인 경우,
        //어떤 원소도 더하지 않은 경우(크기가 0인 부분수열의 합)를 제외한다.
        if(S == 0) {
            countA--;
            countB--;
        }
        System.out.println("binary search: " + countA);
        System.out.println("two pointer: " + countB);
    }

    static int lowerBound(int target, int[] sum2) {
        int low = 0;
        int high = sum2.length;
        while(low < high) {
            int mid = (low + high) / 2;
            if (sum2[mid] >= target)
                high = mid;
            else if (sum2[mid] < target)
                low = mid + 1;
        }
        return low;
    }

    static int upperBound(int target, int[] sum2) {
        int low = 0;
        int high = sum2.length;
        while(low < high) {
            int mid = (low + high) / 2;
            if (sum2[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
