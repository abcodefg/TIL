package algorithm.leetcode.medium.Minimize_Maximum_of_Array;

public class Solution {
    /**
     * 예를 들어, [3, 7, 1, 6]이 입력값으로 주어졌다고 하자.
     * 첫 번째 원소인 3은 더 작아지지 않을 것이므로, 주어진 연산을 거친 후의 배열의 최대값은 적어도 3 이상일 것이다.
     * 또한, 최댓값을 최소화하기 위해 균등하게 분배하면 가장 큰 원소는 전체 평균인 (3+7+1+6) / 4 를 올림한 값인 5 이하일 것이다.
     *
     * 배열의 원소를 처음부터 하나씩 확장해나가며 가장 작은 최대값 max를 구해보자.
     * 1) 배열이 [3]이라면, max는 3이다.
     * 2) 배열이 [3, 7]이라면, max는 5이다.
     * 3) 배열이 [3, 7, 1]이라면, 평균을 올림한 값은 4지만 max는 여전히 5이다.
     *      분배는 오른쪽에서 왼쪽 방향으로만 이루어질 수 있으므로, 마지막으로 추가된 원소가 기존 max보다 크지 않다면 max는 변하지 않는다.
     * 4) 배열이 [3, 7, 1, 6]이라면, 마지막 원소가 기존 max보다 크지만 평균을 올림한 값은 5이므로 max는 5이다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 6 ms (Beats 98.21%)
     * Memory : 61.7 MB (Beats 70.97%)
     */
    public int solution1(int[] nums) {
        long ans = nums[0], sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];

            if (nums[i] > ans) {
                ans = Math.max(ans, (long) Math.ceil((double) sum / (i + 1)));
            }
        }

        return (int) ans;
    }

    public int solution2(int[] A) {
        long sum = 0, res = 0;
        for (int i = 0; i < A.length; ++i) {
            sum += A[i];
            // 수학적으로 Math.ceil(a / b)가 (a+b-1) / b 라는 점을 활용
            res = Math.max(res, (sum + i) / (i + 1));
        }
        return (int)res;
    }
}
