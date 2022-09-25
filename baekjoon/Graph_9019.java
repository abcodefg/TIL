package baekjoon;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

//#9019 DSLR
/*네 개의 명령어를 이용해 숫자 n을 변환할 수 있다.
* D: n을 두 배로 바꾼다. 9999보다 큰 경우 10000으로 나눈 나머지로 바꾼다.
* S: n에서 1을 뺀 결과로 바꾼다. n이 0이라면 9999로 바꾼다.
* L: n의 각 자릿수를 왼쪽으로 이동시킨다.
* R: n의 각 자릿수를 오른쪽으로 이동시킨다.
* A에서 B로 변환하기 위해 필요한 최소한의 명령어 나열을 출력하라.*/
public class Graph_9019 {
    //처음 풀이에선 Map으로 각 숫자의 명령어를 기록하고 방문 여부를 체크했다.
    //정답 판정을 받긴 했지만, 수행 시간이 너무 오래 걸렸다.
    //String 배열에 명령어를 기록하고 boolean 배열로 방문여부를 체크하는 방식으로 리팩토링을 했고
    //수행시간이 거의 절반으로 줄어들었다.
    static char[] command = {'D','S','L','R'};
    static String[] commandArr = new String[10000];
    static boolean[] visited = new boolean[10000];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Arrays.fill(commandArr,"");
            visited = new boolean[10000];
            bfs(x, y);
            System.out.println(commandArr[y]);
        }
    }

    static void bfs(int cur, int dst) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(cur);

        while(!que.isEmpty()) {
            cur = que.poll();
            String currCommands = commandArr[cur];
            if(cur == dst) {
                return;
            }
            for(int i = 0; i < 4; i++) {
                char nextCommand = command[i];
                int nextNum = dslr(cur, nextCommand);
                if(!visited[nextNum]) {
                    visited[nextNum] = true;
                    commandArr[nextNum] = currCommands + nextCommand;
                    que.add(nextNum);
                }
            }
        }
    }

    static int dslr(int num, char command) {
        switch (command) {
            case 'D': num = (num * 2) % 10000;
                break;
            case 'S': num = (num != 0) ? num - 1 : 9999;
                break;
            case 'L': num = (num % 1000) * 10 + (num / 1000);
                break;
            case 'R': num = (num / 10) + (num % 10) * 1000;
                break;
        }
        return num;
    }
}
