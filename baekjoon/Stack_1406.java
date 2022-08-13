package baekjoon;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

// #1406 에디터 풀이
/*편집기에 입력된 문자열을 일련의 명령어들을 입력받아 수정하는 문제.
* L : 커서를 왼쪽으로, D : 커서를 오른쪽으로 한 칸 옮김, B : 커서 왼쪽에 있는 문자를 삭제
* P $ : $라는 문자를 커서 왼쪽에 추가.*/
public class Stack_1406 {
    // LinkedList를 써도 되지만 번거롭다. Stack을 쓰면 훨씬 간단하게 풀리는 문제.
    // 다만, 해당 문제의 풀이에 Stack을 쓸 수 있다는 점을 떠올리기가 조금 까다롭다.
    // 적절하게 활용한다면 Stack이 코드 구현이나 실행시간을 크게 단축시켜줄 수 있으므로
    // 유사한 유형을 만났을 때 참고하도록 하자.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        int M = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        Stack<Character> tempstack = new Stack<>();
        for(int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        // 스택을 두 개 만들어서 커서가 왼쪽으로 이동하면 오른쪽 문자를 임시스택에 담아둔다.
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "L" :
                    if(!stack.isEmpty())
                        tempstack.push(stack.pop());
                    break;
                case "D" :
                    if(!tempstack.isEmpty())
                        stack.push(tempstack.pop());
                    break;
                case "B" :
                    if(!stack.isEmpty())
                        stack.pop();
                    break;
                case "P" :
                    stack.push(st.nextToken().charAt(0));
            }
        }
        while(!stack.isEmpty()) {
            tempstack.push(stack.pop());
        }

        while(!tempstack.isEmpty()) {
            bw.write(tempstack.pop());
        }
        bw.flush();
        bw.close();
    }
}
