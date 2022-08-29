package algorithm;

import java.util.Stack;

// 문자열을 입력받아 모든 괄호의 짝이 맞는지 판별하는 문제
// 괄호는 먼저 열려야 하고, 열린 만큼만 닫혀야 한다.
// 괄호의 종류는 (), {}, [] 세가지가 있으며, 먼저 열린 괄호는 나중에 닫혀야 한다.
    // "[{()}]" (O)  <-> "[{(]})" (X)
public class Stack_balancedBrackets {
    public boolean balancedBrackets(String str) {
        // 두 개의 스택을 이용한다.
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();
        // 모든 문자열을 왼쪽 스택에 입력받는다
        for (char ch : str.toCharArray()) {
            left.push(ch);
        }
        while(!left.isEmpty()) {
            // 왼쪽 스택의 위에서부터 하나씩 꺼내서
            char current = left.pop();
            // 1) 닫는 괄호라면 오른쪽 스택에 담고
            if (current == ')' || current == '}' || current == ']') {
                right.push(current);
            }
            // 2) 여는 괄호라면 오른쪽 스택의 맨 위에서 꺼낸 문자가 닫는 괄호가 아니거나
            //    오른쪽 스택이 비어있다면 false를 반환한다.
            else if (current == '(') {
                if (right.size() == 0 || right.pop() != ')') {
                    return false;
                }
            }
            else if (current == '{' || current == '[') {
                if (right.size() == 0 || right.pop() != current + 2) {
                    return false;
                }
            }
        }
        // 왼쪽 스택을 다 비웠음에도 오른쪽 스택에 닫는 괄호가 남아있다면 false를 반환한다.
        if(!right.isEmpty())
            return false;

        return true;
    }
}
