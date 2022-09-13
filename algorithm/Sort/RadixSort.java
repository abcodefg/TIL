package algorithm.Sort;

import java.util.Arrays;

// 기수정렬
/*낮은 자릿수부터 높은 자릿수까지 계수정렬을 하여 정렬한다(LSD).
* 시간복잡도 O(dn), 안정정렬,
* 각 자릿수에 해당하는 숫자의 정보를 담을 추가적인 메모리 공간(bucket)이 필요*/
public class RadixSort {
    static void radixSort(int[] arr) {
        // 배열에서 가장 큰 수를 기준으로 탐색할 자릿수의 개수를 구함
        int max = 0;
        for(int i = 0; i < arr.length; i++)  {
            if(max < arr[i]) {
                max = arr[i];
            }
        }
        // TODO 확인할 것 (1): 반복문 구성하는 방식
        for(int place = 1; max / place > 0; place *= 10) {
            countingSort(arr, place);
        }
    }
    // 입력받은 자릿수를 기준으로 배열을 정렬
    static void countingSort(int[] arr, int place) {
        int[] newArr = new int[arr.length];
        int[] count = new int[10];

        for (int i = 0; i < arr.length; i++) {
        // TODO 확인할 것 (2): 자릿수 나타내는 방식
            count[(arr[i] / place) % 10]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i - 1] + count[i];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            newArr[count[(arr[i] / place) % 10] - 1] = arr[i];
            count[(arr[i] / place) % 10]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = newArr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{24, 127, 3, 9, 68, 55};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
