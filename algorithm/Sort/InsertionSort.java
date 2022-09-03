package algorithm.Sort;

// 삽입정렬
/*크기가 N인 수열에서 i(2<=i<=N)번째 원소를 순서상 앞에 있는 원소(들)과 비교하고 적절한 위치에 삽입하여 부분적으로 정렬한다.
* 이후 i를 순차적으로 늘려서 끝까지 정렬한다*/
public class InsertionSort {
    public int[] insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] > key) {
                    arr[j + 1] = arr[j];
                    arr[j] = key;
                }
            }
        }
        return arr;
    }
}
