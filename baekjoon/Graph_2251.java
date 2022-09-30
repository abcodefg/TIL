package baekjoon;

import java.util.*;

//#2251 물통
/*부피가 A, B, C인 물통 3개가 있다.
* 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득 차 있다.
* 이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데,
* 이때 한 물통이 비거나 다른 한 물통이 가득 찰 때까지 붓는다.
* 이 과정을 거치면서 A 물통이 비었을 때 C 물통에 담겨있을 수 있는 물의 양을 모두 구하시오.*/
public class Graph_2251 {
    //bfs와 dfs로 풀어봤다.
    //사실 사용하는 자료구조와 그로 인한 탐색하는 순서 외에는 거의 동일하다.
    //다만 둘 다 사용할 수 있고 유사한 성능을 기대할 수 있는 대표적인 유형의 문제인 것 같아 기록한다.
    //메모리와 실행시간으로 놓고 보면 dfs가 근소하게 우수했다.
    static int[] capacity = new int[3];
    static List<Integer> bfsAnswer = new ArrayList<>();
    static List<Integer> dfsAnswer = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 3; i++) {
            capacity[i] = sc.nextInt();
        }
        bfs(new int[]{0, 0, capacity[2]});
        dfs(new int[]{0, 0, capacity[2]});
        Collections.sort(bfsAnswer);
        Collections.sort(dfsAnswer);
        System.out.println("BFS answer: ");
        for (int x : bfsAnswer) {
            System.out.print(x + " ");
        }
        System.out.println("\n" + "DFS answer: ");
        for (int y : dfsAnswer) {
            System.out.print(y + " ");
        }
    }

    static void bfs(int[] start) {
        Queue<int[]> que = new ArrayDeque<>();
        //TODO 확인할 것: 배열 타입 데이터의 방문 여부 String 타입으로 체크
        Set<String> set = new HashSet<>();
        que.add(start);
        set.add(Arrays.toString(start));

        while(!que.isEmpty()) {
            int[] current = que.poll();
            //A가 비어있을 경우, C의 용량을 리스트에 더한다.
            if(current[0] == 0) {
                bfsAnswer.add(current[2]);
            }
            //i에서 j로 물을 붓는다.
            for (int i = 0; i < 3; i++) {
                if (current[i] == 0) continue;
                for (int j = 0; j < 3; j++) {
                    if (i == j || current[j] == capacity[j]) continue;
                    //i가 비어있지 않으면서 j도 꽉 차있지 않은 상황
                    //i로 j를 꽉 채울 수 있을 때와 i로 j를 꽉 채울 수 없을 때로 나눈다.
                    int[] next = current.clone();
                    int pour = Math.min(current[i], capacity[j] - current[j]);
                    next[i] -= pour;
                    next[j] += pour;
                    String nextSt = Arrays.toString(next);
                    if(set.contains(nextSt)) continue;
                    que.add(next);
                    set.add(nextSt);
                }
            }
        }
    }

    static void dfs(int[] start) {
        Stack<int[]> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        stack.push(start);
        set.add(Arrays.toString(start));

        while(!stack.isEmpty()) {
            int[] current = stack.pop();
            //A가 비어있을 경우, C의 용량을 리스트에 더한다.
            if(current[0] == 0) {
                dfsAnswer.add(current[2]);
            }
            //i에서 j로 물을 붓는다.
            for (int i = 0; i < 3; i++) {
                if (current[i] == 0) continue;
                for (int j = 0; j < 3; j++) {
                    if (i == j || current[j] == capacity[j]) continue;
                    //i가 비어있지 않으면서 j도 꽉 차있지 않은 상황
                    //i로 j를 꽉 채울 수 있을 때와 i로 j를 꽉 채울 수 없을 때로 나눈다.
                    int[] next = current.clone();
                    int pour = Math.min(current[i], capacity[j] - current[j]);
                    next[i] -= pour;
                    next[j] += pour;
                    String nextSt = Arrays.toString(next);
                    if(set.contains(nextSt)) continue;
                    stack.push(next);
                    set.add(nextSt);
                }
            }
        }
    }
}
