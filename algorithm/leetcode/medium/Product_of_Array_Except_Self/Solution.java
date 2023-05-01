package algorithm.leetcode.medium.Product_of_Array_Except_Self;

public class Solution {
    /**
     *  [공간 복잡도가 O(n)인 풀이]
     *  누적'합'을 응용한 누적'곱'을 활용해 풀었다.
     *  어떤 원소 x를 제외한 나머지 원소들의 곱은 첫 원소에서 시작해 x 직전 원소까지의 곱에 끝 숫자부터 x 바로 다음 원소까지의 곱을 곱한 값이다.
     *  따라서 배열의 처음 / 끝부터 특정 인덱스까지의 구간 곱의 배열이 prefix, postfix일 때,
     *  숫자 nums[i]를 제외한 나머지 숫자들의 곱은 prefix[i - 1] * postfix[i + 1] 이다.
     *
     *  Time : O(n)
     *  Space : O(n)
     *  Runtime : 2 ms (49.69%)
     *  Memory : 45.5 MB (27.85%)
     */
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] prefix = new int[len];
        int[] postfix = new int[len];
        int[] ans = new int[len];

        // 배열의 처음 / 끝 원소부터 어떤 숫자까지의 누적곱을 구한다.
        prefix[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] * nums[i];
        }
        postfix[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            postfix[i] = postfix[i + 1] * nums[i];
        }

        // nums[i]를 제외한 나머지 숫자들의 곱은 prefix[i - 1] * postfix[i + 1]
        ans[0] = postfix[1];
        ans[len - 1] = prefix[len - 2];
        for (int i = 1; i < len - 1; i++) {
            ans[i] = prefix[i - 1] * postfix[i + 1];
        }

        return ans;
    }

    /**
     *  [공간 복잡도가 O(1)인 풀이]
     *  결과값을 리턴할 배열에 계산 결과를 저장하면 따로 누적곱의 배열을 만들지 않고 풀 수 있다.
     *
     *  Time : O(n)
     *  Space : O(1)
     *  Runtime : 2 ms (49.69%)
     *  Memory : 45.5 MB (27.85%)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        // 앞부분의 누적곱을 배열에 삽입한다
        int product = 1;
        for (int i = 1; i < ans.length; i++) {
            product *= nums[i - 1];
            ans[i] = product;
        }

        // 뒷부분의 누적곱을 앞부분의 누적곱에 곱한다
        product = 1;
        ans[0] = 1;
        for (int i = len - 2; i >= 0; i--) {
            product *= nums[i + 1];
            ans[i] *= product;
        }

        return ans;
    }
}
