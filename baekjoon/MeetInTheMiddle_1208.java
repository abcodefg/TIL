package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class MeetInTheMiddle_1208 {
    //시간복잡도를 줄이기 위한 접근법
    //추후 수정이 필요하다.
    static int count = 0;
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

        for(int i = 0; i < sum1.length; i++)
            for(int j = 0; j < N/2; j++)
                if((i & (1 << j)) > 0)
                    sum1[i] += arr[j];

        for(int i = 0; i < sum2.length; i++)
            for(int j = 0; j < N - N/2; j++)
                if((i & (1 << j)) > 0)
                    sum2[i] += arr[N/2 + j];

        Arrays.sort(sum2);

        for (int j : sum1) {
            binarySearch(S - j, sum2);
        }
        if(S == 0) count--;
        System.out.println(count);
    }

    static void binarySearch(int target, int[] sum2) {
        int low = 0;
        int high = sum2.length - 1;
        while(low <= high) {
            int mid = (low + high) / 2;
            if (sum2[mid] < target)
                low = mid + 1;
            else if (sum2[mid] > target)
                high = mid - 1;
            else {
                count++;
                return;
            }
        }
    }
}
