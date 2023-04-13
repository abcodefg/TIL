package algorithm.leetcode.medium.Validate_Stack_Sequences;

import java.util.Stack;

public class Solution {
    /**
     * [스택을 사용하는 풀이]
     * 스택에 pushed의 숫자들을 하나씩 push한다.
     * 이때, 스택에 있는 숫자가 popped의 숫자와 일치한다면 스택을 비운다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 3 ms (85.90%)
     * Memory : 42.7 MB (19.44%)
     */
    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        int i = 0;
        Stack<Integer> st = new Stack<>();

        for (int n : pushed) {
            st.push(n);

            while (!st.isEmpty() && st.peek() == popped[i]) {   // 스택의 마지막 수가 popped의 요소와 같다면 pop한다
                st.pop();
                i++;
            }
        }

        return st.isEmpty();
    }

    /**
     * [주어진 배열을 스택으로 활용하는 풀이]
     *
     * 스택을 따로 생성하지 않고 주어진 배열을 스택으로 활용하는 풀이이다.
     * 입력된 데이터를 변경한다는 점이 깔끔하진 않지만 시간, 공간복잡도 측면에서 더 나은 풀이이긴 하다.
     * pushed의 인덱스 i는 push가 이루어질 위치, i - 1은 스택의 마지막으로 push된 숫자의 위치라고 생각하면 된다.
     *
     * Time : O(n)
     * Space : O(1)
     * Runtime : 0 ms (100%)
     * Memory : 42.4 MB (43.6%)
     */
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        int i = 0, j = 0;

        for (int n : pushed) {
            pushed[i++] = n;    // stack.push(n)에 해당하는 코드

            while (i > 0 && pushed[i - 1] == popped[j]) {   //
                i--;
                j++;
            }
        }

        return i == 0;
    }
}
