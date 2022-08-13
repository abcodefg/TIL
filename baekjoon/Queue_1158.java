package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// #1158 요세푸스 문제 풀이
/*1번부터 N번까지 N명의 사람들이 원을 이루며 앉아있고 양의 정수 K(K<=N)가 주어진다.
* N명의 사람이 모두 제거될 때까지 원을 따라 순서대로 K번째 사람을 제거한다.
* 이때 제거되는 사람의 번호를 순서대로 나열해서 출력하는 문제*/
public class Queue_1158 {
    // 큐를 사용했을 때 수월하게 풀리는 문제.
    // 변동하는 배열을 반복 순회하며 조건에 맞는 원소를 찾아야 할 때 큐를 사용할 수 있다는 것을 기억해두자.
    // 이 문제에선 큐의 원소를 앞에서 빼고 뒤로 넣는 과정을 반복해 순회하며,
    // count를 통해 해당 원소가 조건(K번째)에 맞는지 확인했다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int count = 1;

        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            que.add(i);
        }

        while(que.size() != 0) {
            if(count == K) {
                list.add(que.poll());
                count = 1;
            }
            else {
                que.add(que.poll());
                count++;
            }
        }

        System.out.println(list.toString().replace("[", "<").replace("]", ">"));
    }
}
