package algorithm.leetcode.medium.New_21_Game;

public class Solution {
    /**
     * [DFS를 활용한 DP 풀이]
     * n = 5, k = 3, maxPts = 5인 경우를 살펴보자.
     *       |---|---|---|---|---|---|---|
     *  i    0   1   2   3   4   5   6   7
     * dp[i]        0.6  1   1   1   0   0
     * dp[i]를 현재 점수(=pts)가 i일 때, 이후 draw를 중단한 시점에 점수가 k 이상  n 이하일 확률이라고 하자.
     * i가 3(=k) 이상 5(=n) 이하일 때 dp[i]는 당연히 1이고, 5보다 클 때는 0일 것이다.
     *
     * dp[2]의 값은 이보다 큰 값에서 역으로 산출할 수 있다.
     * 카드를 뽑으면 1 ~ 5(=maxPts)까지의 숫자가 동일한 확률로 나올 것이고
     * 따라서 현재 점수가 2일 때 최종 점수가 3~7일 확률은 (1+1+1+0+0) / 5(=maxPts) = 0.6이다.
     *
     * 마찬가지로 dp[1]의 값은 2<=i<=6인 구간의 dp[i]값의 합인 (0.6+1+1+1+0)를 5로 나누어 구할 수 있으며,
     * 정답인 dp[0] 역시 마찬가지의 방식으로 구할 수 있다.
     *
     * 그러나 아래와 같이 브루트 포스로 풀이할 경우 메모이제이션을 적용하더라도 0 ~ k-1까지 k 개의 값에 대하여 maxPts 번의 반복문을 수행해야 하므로
     * 시간복잡도가 O(k * maxPts)이고 k와 maxPts가 둘 다 10^4 이하이므로 많게는 10^8 번의 연산을 거쳐야 하므로 비효율적이다.
     * 실제로도 이 풀이는 시간이 초과됐다.
     *
     * Time : O(k * maxPts) (재귀 호출 * 호출 1번 당 반복문)
     * Space : O(k) (dp 배열, 재귀 호출)
     * Runtime : 시간 초과
     */
    public double new21Game_DFS(int n, int k, int maxPts) {
        if (n - k + 1 >= maxPts) return 1.0;
        dp = new Double[k];

        return dfs(n, k, maxPts, 0);
    }

    private Double[] dp;
    private double dfs(int n, int k, int maxPts, int pts) {
        if (pts >= k) {
            if (pts <= n) return 1;
            else return 0;
        }

        if (dp[pts] != null) return dp[pts];

        double prob = 0;
        for (int i = 1; i <= maxPts; i++) {
            prob += dfs(n, k, maxPts, pts + i);
        }

        return dp[pts] = prob / maxPts;
    }

    /**
     * [Sliding Window를 활용한 풀이]
     * 다시 아까의 예시를 살펴보자. (maxPts=5)
     *                   k       n
     *       |---|---|---|---|---|---|---|
     *  i    0   1   2   3   4   5   6   7
     * dp[i]        0.6  1   1   1   0   0
     *
     * dp[2]를 구하기 위해서는 dp[3] + ... + dp[7]을,
     * dp[1]를 구하기 위해서는 시작과 끝 지점을 한 칸씩 옆으로 옮긴 dp[2] + ... + dp[6]을 계산해야 한다.
     * 그런데, dp[2] + ... + dp[6]는 dp[3] + ... + dp[7]에서 시작, 끝 인덱스를 한 칸씩 왼쪽으로 옮긴 값 즉,
     * dp[3] + ... + dp[7]에 dp[2]를 더하고 dp[7]을 뺀 값이므로 sliding window 알고리즘을 적용할 수 있다.
     *
     * Time : O(n) = O(n - k + k) (for문의 시간 복잡도 + while문의 시간 복잡도)
     * Space : O(k + maxPts)
     * Runtime : 0 ms (100%)
     * Memory : 40.4 MB (89.85%)
     */
    public double new21Game_SlidingWindow(int n, int k, int maxPts) {
        // n 이상 k 이하인 구간의 길이(= n - k + 1) >= maxPts라면
        // 점수가 k-1인 상황에서 카드를 뽑으면 반드시 이 구간 내에 다음 값이 있으므로 확률이 1이며,
        // 마찬가지의 이유로 dp[0] ~ dp[k-2] 모두 1이다
        if (n - k + 1 >= maxPts) return 1.0;

        double[] dp = new double[k + maxPts];
        // dp[i]의 합
        double sum = n - k + 1;
        // 현재 점수가 k 이상 n 이하일 때 확률은 1
        for (int i = k; i <= n; i++) {
            dp[i] = 1.0;
        }
        // l, r : 구간을 한 칸 옮겼을 때 더할 값/뺄 값의 인덱스
        int l = k - 1, r = k + maxPts - 1;

        while (l >= 0) {
            dp[l] = sum / (double) maxPts;
            sum += dp[l];
            sum -= dp[r];
            l--;
            r--;
        }

        return dp[0];
    }
}
