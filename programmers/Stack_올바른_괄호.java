package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 ')' 문자로 닫혀야 한다는 뜻입니다.
* '(' 또는 ')'로만 이루어진 문자열 s가 주어졌을 때
* s가 올바른 괄호이면 true, 아니면 false를 return하는 함수를 완성하세요.*/
public class Stack_올바른_괄호 {
    /**
     * 정답은 도출했으나 실행시간이 초과한 코드
     * 그럴 필요가 없는데도 굳이 스택을 두 개 쓴 게 잘못이었다.
     *
     * 비슷한 논리의 접근이지만 스택을 하나만 쓰거나 아예 쓰지 않고도 풀이가 가능했다.
     * (1) 불필요한 요소를 제거하거나 (2) 보다 간단한 접근법을 차용함으로써
     * 코드를 간결하고 효율적으로 만드는 대표적인 예시인 것 같다.
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
     *
     * 1번의 잘못된 풀이는 문자열이 올바른 괄호인지를 뒤에서부터 검사하는데,
     * 올바른 괄호는 앞에서부터 검사하든 뒤에서부터 검사하든 올바른 괄호이므로
     * 스택에 괄호들을 순차적으로 넣으면서 올바른 괄호인지를 앞쪽부터 검사한다.
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

    /**
     * 스택을 사용하지 않는 풀이
     *
     * 다른 분의 풀이 중 참고할 만한 풀이를 기록한다.
     * 앞의 두 풀이와 기본적인 개념은 동일하다.
     * 2번 풀이의 c == ')'일 때 stack의 크기가 0인 경우가
     * 3번 풀이의 count < 0인 경우에 해당한다.
     *
     * 이 예시에서 알 수 있듯, 3번 풀이는 2번 풀이에 비해 동일한 상황도 훨씬 간결하게 표현하며,
     * Stack에 값을 저장하지 않고 실행시간 역시 1/5 이하에 불과하므로
     * 공간복잡도, 시간복잡도의 측면에서 보다 우수한 풀이이다.
     */
    boolean solution_pass2(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }

            if (count < 0) {
                return false;
            }
        }

        if (count > 0)
            return false;

        return true;
    }
}
