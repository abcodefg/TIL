package algorithm.leetcode.easy.Best_Time_To_Buy_And_Sell_Stock;

public class Solution {
    /**
     * prices = [7,1,5,3,6,4]인 경우를 살펴보자.
     * 1일차(price = 7)에 사서 2일차(price = 1)에 판다면 어떠한 수익도 얻을 수 없다.
     * 또한, 3일차 이후부터 주식을 파는 경우에 1일차보다는 가격이 낮은 2일차에 사는 것이 유리하므로,
     * 주식을 구매하는 시점을 2일차로 옮기는 편이 보다 좋은 수익을 기대할 수 있다.
     *
     * 2일차(price = 1)에 사서 3일차(price = 5)에 판다면 수익을 얻을 수 있다.
     * 최고 수익 여부를 확인하고, 판매일을 옮겨가며 최고 수익을 경신한다면 기록한다.
     * 단, 이후에도 2일차보다 가격이 낮은 일자가 나타난다면 구매하는 시점을 옮긴다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 2 ms (92.95%)
     * Memory : 60.7 MB (5.12%)
     */
    public int maxProfit(int[] prices) {
        int l = 0, r = 1, max = 0;
        while (r < prices.length) {
            if (prices[r] > prices[l]) {
                int profit = prices[r] - prices[l];
                max = Math.max(max, profit);
            } else {
                l = r;
            }

            r++;
        }

        return max;
    }
}
