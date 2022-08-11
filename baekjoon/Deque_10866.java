package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// #10866 덱 풀이
/*양방향으로 데이터를 넣고 뺄 수 있는 자료구조인 덱(Deque)를 구현하는 문제
*addFirst, addLast, removeFirst, removeLast 등의 메서드를 연습해볼 수 있었다*/
public class Deque_10866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            switch (str) {
                case "push_front" :
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    if(deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.removeFirst());
                    break;
                case "pop_back":
                    if(deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.removeLast());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if(deque.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "front":
                    if(deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peek());
                    break;
                case "back":
                    if(deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peekLast());
                    break;
            }
        }
    }
}
