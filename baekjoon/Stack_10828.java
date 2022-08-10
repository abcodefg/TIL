package baekjoon;

import java.io.*;
import java.util.*;

// # 10828 스택 문제풀이
/*스택을 구현하고 사용해보는 문제
* push(): 스택에 데이터 추가
* pop(): 가장 나중에 추가된 데이터를 스택에서 삭제하고 그 값을 반환
* size(): 스택에 추가된 데이터 크기 반환
* peek(): 가장 나중에 추가된 데이터를 반환
* show(): 현재 스택에 포함된 모든 데이터를 String 타입으로 반환
* clear(): 모든 데이터 삭제*/
public class Stack_10828 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st;

        for(int i = 0; i < N; i++) {
            String command = br.readLine();
            if(command.startsWith("push")) {
                stack.push(Integer.parseInt(command.substring(5)));
            }
            else if (command.equals("pop")) {
                if(stack.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(stack.pop());
            }
            else if (command.equals("size")) {
                System.out.println(stack.size());
            }
            else if (command.equals("empty")) {
                if(stack.size() == 0)
                    System.out.println(1);

                else
                    System.out.println(0);

            }
            else if (command.equals("top")) {
                if(stack.size() == 0)
                    System.out.println(-1);
                else
                    System.out.println(stack.peek());
            }
        }
    }
}
