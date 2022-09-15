package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//#1963 소수 경로
/*4자리 소수 A로 이루어진 비밀번호를 다른 4자리 소수 B로 변경하려고 한다.
* 한 번에 한 자리씩만 바꾸며, 바꾼 숫자 역시 계속 소수여야 한다고 할 때,
* A에서 B로 변경하는 데 필요한 최소 몇 단계를 거쳐야 하는지 구하라.
* 단, 변경이 불가능하면 Impossible을 출력한다.*/
public class Graph_1963 {
    // 에라스토테네스의 체를 활용하여 소수의 목록을 만들었다.
    // 이후 BFS로 숫자를 변경하는 경우를 모두 탐색한다.
    static boolean[] isNotPrime = new boolean[10000];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        // TODO 확인할 것 (1): 에라스토테네스의 체
        //  소수 판독 배열을 생성하는 방식을 변경했다.
        //  기존 '소수이면 참'인 배열을 만드는 방식에 비해
        //  간결하고 조건이 명료하다.
        for(int i=2; i<10000; i++) {
            if(!isNotPrime[i]) {
                for(int j=i*i; j<10000; j+=i) {
                    isNotPrime[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int curPwd = Integer.parseInt(st.nextToken());
            int newPwd = Integer.parseInt(st.nextToken());
            sb.append(bfs(curPwd, newPwd) + "\n");
        }
        System.out.println(sb);
    }

    static String bfs(int curPwd, int newPwd) {
        Queue<Integer> que = new ArrayDeque<>();
        // TODO 확인할 것 (2): Map 사용
        //  visited 배열을 대신하고, 탐색의 level을 기록할 수 있다.
        Map<Integer, Integer> map = new HashMap<>();

        map.put(curPwd, 0);
        que.add(curPwd);

        while(!que.isEmpty()) {
            int current = que.poll();
            int count = map.get(current);
            // 큐에서 출력한 숫자가 변경하고자 하는 비밀번호라면 메서드를 종료한다.
            if(current == newPwd) {
                return count + "";
            }
            // 자릿수를 탐색하는 반복문
            for(int place = 1; place < 10000; place*=10) {
                // 각 자릿수의 숫자를 탐색하는 반복문
                for(int digit = 0; digit <= 9; digit++) {
                    // 천의 자리가 0인 경우는 제외한다.
                    if(digit == 0 && place == 1000)
                        continue;
                    // 현재 숫자에서 'place'의 자릿수를 구한다.
                    int digitNum = (current / place) % 10;
                    if(digitNum != digit) {
                        int next = current +(digit - digitNum) * place;
                        if(!isNotPrime[next] && !map.containsKey(next)) {
                            que.add(next);
                            map.put(next, count + 1);
                        }
                    }
                }
            }
        }
        return "Impossible";
    }
}
