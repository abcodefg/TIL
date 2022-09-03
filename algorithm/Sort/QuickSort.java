package algorithm.Sort;

import java.util.Arrays;

// 퀵소트
/*수열 내부의 한 숫자 x를 기준으로 그 왼쪽에는 작은 수, 오른쪽에는 큰 수가 오도록 정렬한 후,
* 수열을 x를 기준으로 왼쪽과 오른쪽의 두 수열로 나눠 각 수열에서 앞선 과정을 반복하는 정렬 방식*/
public class QuickSort {
    // pivot보다 작은 값, 큰 값, 같은 값을 담은 리스트를 생성해 이를 합쳐 반환하는 방법도 있으나
    // 재귀 호출할 때마다 새로운 리스트를 생성해야 하므로 메모리 사용 측면에서 비효율적이다.
    // 따라서 입력받은 배열만을 사용하는 in-place 알고리즘이 선호된다.
    static int[] arr;
    public static void main(String[] args) {
        arr = new int[]{5,7,8,6,4,2,3,1,9};
        sort(0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    static void sort(int start, int end) {
        if(start >= end) {
            return;
        }
        int mid = partition(arr, start, end);
        sort(start, mid - 1);
        sort(mid, end);
    }
    // 입력받은 배열에서 pivot의 왼쪽에는 pivot보다 작은 값,
    // 오른쪽에는 pivot보다 큰 값만 존재하도록 정렬한다.
    // 이후 중간값을 반환한다.
    static int partition(int[] arr, int start, int end) {
        // 배열의 가운데 값을 pivot으로 지정한다.
        int pivot = arr[(start + end) / 2];

        while(start <= end) {
            while(arr[start] < pivot) start++;
            while(arr[end] > pivot) end--;
            if(start <= end) {
                swap(arr, start, end);
                start++;
                end--;
            }
        }
        return start;
    }
    // 입력받은 배열에서 두 요소의 값을 바꾼다.
    static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
