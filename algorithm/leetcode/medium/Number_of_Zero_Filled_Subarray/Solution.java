package algorithm.leetcode.medium.Number_of_Zero_Filled_Subarray;

public class Solution {
    /**
     * 연속된 0의 개수에 따른 부분집합의 개수의 패턴을 탖아보자.
     * 0    ->  1개
     * 00   ->  3개 (2개 증가)
     * 000  ->  6개 (3개 증가)
     * 0000 ->  10개 (4개 증가)
     * ...
     *
     * 연속된 n개의 0에 0이 하나 더 붙을 경우 부분집합의 개수가 n+1개 증가하는 것을 알 수 있다.
     * 이는 기존의 각 0들에서 시작해 마지막에 추가된 0에서 끝나는 부분집합들(n개) + 마지막 0(1개)가 더해지기 때문이다.
     * 예를 들어, 00 -> 000의 경우 더해지는 부분집합들은 다음과 같다.
     *      00
     *      000
     *       00
     *        0
     *
     * 위의 규칙을 활용하여 풀이를 작성했다.
     * Time : O(n)
     * Space : O(1)
     * Runtime : 3 ms (99.84%)
     * Memory : 60 MB (52.66%)
     */
    public long zeroFilledSubarray1(int[] nums) {
        long ans = 0, cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                cnt = 0;
            } else {
                cnt++;
                ans += cnt;
            }
        }

        return ans;
    }
}
