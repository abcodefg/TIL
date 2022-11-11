package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//괄호가 바르게 짝지어졌단느 것은 '(' 문자로 열렸으면 ')' 문자로 닫혀야 한다는 뜻입니다.
public class Stack_올바른_괄호 {
    /**
     * 정담은 도출했으나 실행시간이 초과한 코드
     * 그럴 필요가 없는데도 굳이 스택을 두 개 쓴 게 잘못이었다.
     * 비슷한 논리의 접근이지만 스택을 하나만 써서 푸는 게 가능했다.
     */
    boolean solution_fail(String s) {
        Stack<Character> front = new Stack<>();
        Stack<Character> back = new Stack<>();

        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }
        front.addAll(list);

        while (!front.isEmpty()) {
            char current = front.pop();
            if (current == ')') {
                back.push(current);
            } else {
                if (back.size() == 0 || back.pop() != ')') {
                    return false;
                }
            }
        }

        if (!back.isEmpty())
            return false;

        return true;
    }

    /**
     * 스택을 하나만 쓰는 풀이
     */
    boolean solution_pass(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.size() == 0) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (stack.size() != 0)
            return false;

        return true;
    }
}
