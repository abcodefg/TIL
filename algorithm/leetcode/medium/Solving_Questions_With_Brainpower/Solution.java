package algorithm.leetcode.medium.Solving_Questions_With_Brainpower;

public class Solution {

    /**
     * [Top-Down DP 풀이]
     * dp(i)가 i번 문제부터 끝 문제까지에서 얻을 수 있는 점수의 최댓값이라고 하자.
     * 만약, questions = [[3,2],[4,3],[4,4],[2,5]] 일 때,
     * dp(0)은
     *  0번 문제를 푼 경우 "3 + dp(0 + 2 + 1)"이고 (0 + 2 + 1은 다음 문제 2개를 skip한 이후의 인덱스)
     *  0번 문제를 풀지 않은 경우 "dp(0 + 1)"이다.
     *
     * 재귀의 base case는 i가 questions 인덱스의 범위를 벗어났을 때이며,
     * 이 때는 추가적으로 풀 수 있는 문제가 없으므로 점수 0을 반환한다.
     *
     * Time : O(n) (n = questions.length())
     * Space : O(n) (재귀로 인한 콜 스택과 Long 배열 둘 다 O(n))
     * Runtime : 5 ms (72.55%)
     * Memory : 43.1 MB (5.6%)
     */
    public long mostPoints_recursive(int[][] questions) {
        mem = new Long[questions.length];

        return dp(0, questions);
    }

    private Long[] mem;
    private long dp(int i, int[][] questions) {
        if (i >= questions.length) {
            return 0;
        }

        if (mem[i] != null) return mem[i];

        return mem[i] = Math.max(dp(i + 1, questions), questions[i][0] + dp(i + questions[i][1] + 1, questions));
    }

    /**
     * [Bottom-Up DP 풀이]
     * 첫 풀이와 동일한 접근을 Bottom-Up 방식으로 구현했다.
     * 단, recursive DP의 경우 직관적이지만, 추가적인 함수 호출의 오버헤드로 인해 실행시간이 iterative DP에 비해 상대적으로 길 수 있다.
     *
     * #참고
     * iterative vs recursive 어느 쪽을 사용해야 할까?
     * -> recursion depth와 time complexity 등을 고려하자.
     * (재귀를 활용한 풀이는 때로는 불필요한 연산 때문에 exponential time complexity로 이어질 수 있지만,
     * 반복문을 활용한 풀이는 대개 linear or polynomial time complexity를 갖는다.
     * input size가 크거나 재귀의 time complexity가 exponential 하다면 반복문을 활용하자.)
     *
     * Time : O(n) (n = questions.length())
     * Space : O(n) (재귀로 인한 콜 스택과 Long 배열 둘 다 O(n))
     * Runtime : 6 ms (85.79%)
     * Memory : 98 MB (45.8%)
     */
    public long mostPoints_iterative(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            int prevIdx = Math.min(len, i + questions[i][1] + 1);
            dp[i] = Math.max(questions[i][0] + dp[prevIdx], dp[i + 1]);
        }

        return dp[0];
    }
}
