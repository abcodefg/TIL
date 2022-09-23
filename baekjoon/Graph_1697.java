package baekjoon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//#1697 숨바꼭질
/*점 N(0 ≤ N ≤ 100,000)에 있는 술래는 위치가 x일 때
 * 1초 후에 x-1 혹은 x+1 혹은 2*x로 이동할 수 있다.
* 술래가 K(0 <= K <= 100,000)에 있는 사람을 찾는 가장 빠른 시간을 구하라.*/
public class Graph_1697 {
    //다른 bfs 문제와 비교했을 때 큐에 다음에 추가될 요소를 더하는 방식이 달랐다.
    //이 부분을 다른 메서드 record로 빼서 처리했다.
    static int n, k, answer;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        arr = new int[100001];
        bfs();
        System.out.println(answer);
    }

    static void bfs() {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(n);
        int sec = 0;
        while(!que.isEmpty()) {
            int current = que.poll();
            sec = arr[current];
            if(current == k) {
                answer = arr[current];
                return;
            }
            record(sec + 1, current, que);
        }
    }
    //다음으로 이동할 위치를 큐에 더하고, 그 위치까지 이동하는 데 걸린 시간을 배열에 기록한다.
    static void record(int sec, int current, Queue<Integer> que) {
        int[] idxArr = {current - 1, current + 1, current * 2};
        for(int i = 0; i < 3; i++) {
            int nextIdx = idxArr[i];
            if(nextIdx >= 0 && nextIdx <= 100000 && arr[nextIdx] == 0) {
                que.add(nextIdx);
                arr[nextIdx] = sec;
            }
        }
    }
}
