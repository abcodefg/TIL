package algorithm.Sort;

import java.util.Arrays;

// 계수정렬
/* 음수를 포함하고 있다면 가장 작은 음수가 0이 되도록 모든 원소에 동일한 값을 더한다.*/
public class CountingSort {
    public static void main(String[] args) {
        int[] unsorted = new int[]{3,2,2,6,16,5,4,23,7,1};
        System.out.println(Arrays.toString(countingSort(unsorted)));
    }
    public static int[] countingSort(int[] unsorted) {
        int max = unsorted[0];
        for(int i = 1; i < unsorted.length; i++) {
            max = Math.max(max, unsorted[i]);
        }
        int[] count = new int[max + 1];

        for(int num : unsorted) {
            count[num]++;
        }

        for(int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + count[i];
        }
        int[] sorted = new int[unsorted.length];

        for(int num : unsorted) {
            sorted[count[num] - 1] = num;
            count[num] -= 1;
        }
        return sorted;
    }
}
