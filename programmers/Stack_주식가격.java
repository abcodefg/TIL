package programmers;
import java.util.*;

/* 초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때,
   가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.
 */
public class Stack_주식가격 {
    /**
     * 어떤 시간대에 대해 가격이 떨어지지 않은 기간을 X라고 하자.
     *
     * 단순하게 접근하자면, 이중 반복문으로 각 시간대에 대해 X를 구할 수도 있다.
     * 그러나 이 풀이는 O(n^2)의 시간복잡도를 가지므로 바람직하지 않다.
     *
     * 나열된 수의 크기에 따라 결정되는 다른 값을 순서대로 따져본다는 점에서
     * 스택이나 큐를 사용해볼 수 있다.
     *
     * prices를 순회하되 가격이 떨어지는 시점에 X를 구하는 과정이 거꾸로 이루어져야 할 것이므로
     * 마지막으로 등록된 데이터를 순차적으로 뺄 수 있는 스택을 사용해보자.
     *
     * 또한, 시간대에 따른 가격 정보는 이미 prices 배열에 저장되어 있기 때문에
     * 스택에는 가격이 아닌 시간 즉, prices의 인덱스를 저장하는 것이 합리적이다.
     *
     * 풀이에 대한 기본적인 접근법이 완성되었다.
     * prices 배열을 순회하면서 특정 조건에 따라 스택에 prices의 인덱스를 저장한다.
     */

    public int[] solution(int[] prices) {

        int[] ans = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        // prices를 순회하며 가격이 떨어진 시간대가 있다면
        for (int i = 0; i < ans.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                // 스택에서 제거한 후, X를 구한다.
                int cur = stack.pop();
                ans[cur] = i - cur;
            }
            // 새로운 시간대를 스택에 추가한다.
            stack.push(i);
        }
        // 이런 식으로 구성한 스택은 항상 모든 요소가 직전 요소보다 크거나 같다.

        // 스택에 남아있는 시간대는 가격이 떨어지지 않았으므로
        // 마지막 시간대를 기준으로 일괄적으로 X를 구한다.
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            ans[cur] = ans.length - cur - 1;
        }

        return ans;
    }
}
