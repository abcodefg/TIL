package algorithm.leetcode.medium.Uncrossed_Lines;

public class Solution {
    /**
     * ["길이 + 1" 크기의 배열을 활용한 DP 풀이]
     * nums1의 인덱스가 i, nums2의 인덱스가 j일 때, dp[i][j] 는 i와 j까지의 두 숫자의 교차하지 않는 선(uncrossed lines)의 최대 개수라고 하자.
     *  nums1의 i가 가리키는 숫자와 nums2의 j가 가리키는 숫자가 같다면, 앞서 그어진 선과 교차하지 않는 선을 하나 더 그을 수 있으므로, dp[i][j]는 dp[i-1][j-1] + 1이다.
     *  두 숫자가 다르다면, dp[i][j]는 직전까지 누적된 교차하지 않는 선의 개수, dp[i - 1][j]와 dp[i][j - 1] 중 보다 큰 값이다.
     *
     * 이때, 크기가 입력하려는 값의 수보다 1 큰 1-indexed array를 만들면 index가 벗어나지 않고 첫 행과 열의 값에 대해서도 같은 식으로 정리할 수 있다.
     *
     * Time : O(m * n) (m -> nums1.length(), n -> nums2.length())
     * Space : O(m * n)
     * Runtime : 5 ms (72.55%)
     * Memory : 43.1 MB (5.6%)
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // 길이 + 1 크기의 배열
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) { // 두 포인터가 가리키는 숫자가 같은 경우
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {                            // 두 포인터가 가리키는 숫자가 다른 경우
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    /**
     * [공간 복잡도를 개선한 풀이]
     * 직전의 코드의 반복문을 보면, 배열의 두 개의 행에 대해서만 연산이 이루어지는 것을 알 수 있다.
     * 예를 들어, 첫 번째 풀이에서 배열 dp의 값이 다음과 같다고 할 때,
     * ...
     * (i-1, j-1) (i-1, j) ...
     * (i, j-1)   (i, j)   ...
     * ...
     *
     * 어떤 원소 (i, j)의 값은 (i-1, j), (i-1, j-1), (i, j-1)의 세 값만 있으면 구할 수 있다.
     * 따라서, i열의 값을 할당할 수 있는 배열 하나와 i-1열에서 필요한 두 값을 할당할 수 있는 지역 변수 두 개만 활용하는 방식으로 처음의 풀이를 최적화할 수 있다.
     *
     * 참고:
     * 두 개의 배열(직전 값을 저장하는 배열 prev와 현재의 값을 저장하는 배열 curr)을 통해 dp 값을 없데이트하는 방법 역시 공간 복잡도가 O(n)이지만,
     * 반복문이 끝날 때마다 curr를 prev에 할당하고, curr에 새로운 int 배열을 생성해 할당해야 하므로, 두 번째 코드보다 메모리가 더 많이 사용될 수 있다.
     *
     * Time : O(m * n) (m -> nums1.length(), n -> nums2.length())
     * Space : O(n)
     * Runtime : 4 ms (72.55%)
     * Memory : 41.1 MB (85.68%)
     */
    public int maxUncrossedLines_modified(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] dp = new int[n+1];
        // 어떤 원소 dp[i][j]에 대하여
        for(int i = 1; i <= m; i++){
            int prev = 0;   // 왼쪽 위 원소의 값 (=dp[i-1][j-1]). i-1번째 연산의 curr 값과 동일.
            for (int j = 1; j <= n; j++) {
                int curr = dp[j]; // 위쪽 원소의 값 (=dp[i-1][j])
                if (nums1[i-1] == nums2[j-1]) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(curr, dp[j-1]);    // dp[j-1]은 왼쪽 원소의 값
                }
                prev = curr;
            }
        }

        return dp[n];
    }
}
