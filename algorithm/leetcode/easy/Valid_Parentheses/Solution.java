package algorithm.leetcode.easy.Valid_Parentheses;

import java.util.Stack;

public class Solution {

    /**
     * [Stack을 활용한 풀이]
     *
     * 여는 괄호인 경우 stack에 push하고, 닫는 괄호인 경우 stack의 최상단에 해당하는 여는 괄호가 있는지 확인한다.
     * 반복문이 종료되었는데 stack에 괄호가 남아있다면 false를 return한다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 2 ms (89.24%)
     * Memory : 40.4 MB (87%)
     */
    public boolean isValid_stack(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') return false;
                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') return false;
                stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') return false;
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    /**
     * [char 배열을 활용한 풀이]
     * 문제에 대한 접근법을 효율적으로 개선하고
     * 이를 배열을 통해 스택 기능을 모방하여 구현한 풀이이다.
     *
     * 예를 들어, 괄호가 "({[" 와 같이 주어졌을 때, 직후에 다른 여는 괄호가 따라붙지 않는다면 이에 상응하는 닫는 괄호 "]})"가 주어져야 한다.
     * 그런데, 이를 확인하기 위해서 앞에 주어진 여는 괄호가 "({["였다는 정보가 아닌, 닫는 괄호 "]})"가 이어져야 한다는 정보만 있어도 충분하며,
     * 따라서 여는 괄호가 주어지면 그에 해당하는 닫는 괄호를 stack에 담고 닫힌 괄호가 주어졌을 때 이와 일치하는지만 확인하면 된다.
     * 이렇게 하면, 닫는 괄호가 주어졌는데 상응하는 여는 괄호가 먼저 나타나지 않은 경우( = stack이 비어 있는 경우)를 마지막 조건문에서 한 번에 처리할 수 있다.
     *
     * 이 풀이는 인덱스를 변경하며 배열에 값을 담아 stack을 구현했다.
     * stack의 크기가 제한적인 경우에 활용할 만하다.
     *
     * Time : O(n)
     * Space : O(n)
     * Runtime : 0 ms (100%)
     * Memory : 60.7 MB (53.85%)
     */

    public boolean isValid_array(String s) {
        char chars[] = new char[s.length()];
        int index = -1;
        for (char current : s.toCharArray()){
            if (current == '(') chars[++index] = ')';
            else if (current == '[') chars[++index] = ']';
            else if (current == '{') chars[++index] = '}';
            else if (index == -1 || chars[index--] != current) return false;
        }
        return index == -1;
    }
}
