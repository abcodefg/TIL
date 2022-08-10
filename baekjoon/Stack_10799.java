package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

// #10799 쇠막대기 풀이
/*"(" 와 ")"로 이루어진 문자열을 입력받아 정해진 규칙에 따라 계산된 특정 정수를 출력하는 문제
* 문제 설명은 백준 사이트의 이미지를 참고할 것. https://boj.kr/10799
* 문제를 스택으로 풀 수 있다는 걸 알아채는 게 풀이의 핵심이었다*/
public class Stack_10799 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(')
                stack.push(str.charAt(i));
            else if (str.charAt(i) == ')') {
                stack.pop();
                if (str.charAt(i - 1) == '(') {
                    count += stack.size();
                } else if (str.charAt(i - 1) == ')') {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }
}