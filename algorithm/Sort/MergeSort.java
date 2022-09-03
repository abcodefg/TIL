package algorithm.Sort;

import java.util.Arrays;

// 병합정렬

public class MergeSort {
    public static void mergeSort(int[] arr) {
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length - 1);
    }

    static void mergeSort(int[] arr, int[] tmp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, tmp, start, mid);
            mergeSort(arr, tmp, mid + 1, end);
            merge(arr, tmp, start, mid, end);
        }
    }

    static void merge(int[] arr, int[] tmp, int start, int mid, int end) {
        for(int i = start; i <= end; i++) {
            tmp[i] = arr[i];
        }
        int part1 = start;
        int part2 = mid + 1;
        int index = start;
        while (part1 <= mid && part2 <= end) {
            if(tmp[part1] <= tmp[part2]) {
                arr[index] = tmp[part1];
                part1++;
            } else {
                arr[index] = tmp[part2];
                part2++;
            }
            index++;
        }
        // TODO 확인할 것 (1): 남은 부분 처리
        //  두 배열에 공통으로 적용할 수 있게 반복문의 i값 지정
        for (int i = 0; i <= mid - part1; i++) {
            arr[index + i] = tmp[part1 + i];
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{6,3,5,4,2,1,9,8,7};
        System.out.println(Arrays.toString(arr));
        // TODO 확인할 것 (2): 배열을 정적 변수에 대입하지 않아도 됨
        //  메서드에서 return값이 배열일 필요도 없음
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
