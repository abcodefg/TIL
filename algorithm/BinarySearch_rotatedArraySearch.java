package algorithm;

// 부분적으로 정렬된 배열 탐색
/*{4, 5, 6, 7, 0, 1, 2, 3}과 같이 부분적으로 오름차순 정렬된 배열과 임의의 숫자 target을 입력받아
* 배열에 target이 존재한다면 그 인덱스를, 존재하지 않는다면 -1을 반환하는 문제*/

// 배운 점 :
// (1) A 제어문 안에 B 제어문을 작성한다고 할 때, B 제어문을 구성하는 언어는 A 제어문과 연동되게끔 작성하는 게 좋다.
// 그래야 A 제어문의 모든 경우를 빠짐없이 다룰 가능성이 높기 때문이다.
// (2) 경우를 구분할 기준은 간단하고 명확한 것들로부터 세워나가는 것이 좋다.
public class BinarySearch_rotatedArraySearch {
    // 이분 탐색을 조금 변형시켜 적용해봤다.

    public int rotatedArraySearch(int[] rotated, int target) {
        int start = 0;
        int end = rotated.length - 1;
        int idx = -1;
        while(start <= end) {
            int mid = (start + end) / 2;
            if (target == rotated[mid]) {
                idx = mid;
                break;
            }
            // 어떤 범위의 중간지점 mid를 기준으로 왼쪽 절반 혹은 오른쪽 절반은 완전히 정렬되어 있는 게 확실하다.
            // target은 정렬된 쪽에 있거나 정렬되지 않은 쪽에 있다.
            // 왼쪽이 완전히 정렬되어 있다면
            if(rotated[start] < rotated[mid]) {
                // target의 값이 왼쪽에 있는 경우 범위를 왼쪽으로 좁힌다.
                if(target < rotated[mid] && target >= rotated[start]) {
                    end = mid - 1;
                }
                // 그렇지 않은 경우 범위를 오른쪽으로 좁힌다.
                else {
                    start = mid + 1;
                }
            }
            // 오른쪽이 완전히 정렬되어 있다면
            if(rotated[end] > rotated[mid]) {
                // target의 값이 오른쪽에 있는 경우 범위를 왼쪽으로 좁힌다.
                if (target > rotated[mid] && target <= rotated[end]) {
                    start = mid + 1;
                }
                // 그렇지 않은 경우 범위를 왼쪽으로 좁힌다.
                else {
                    end = mid - 1;
                }
            }
        }
        return idx;
    }
}
