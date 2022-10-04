package baekjoon;

import java.util.*;

//#5014 스타트링크
/*어떤 건물에 엘리베이터가 있다.
* 이 엘리베이터는 U버튼/D버튼을 누르면 U/D 층만큼 위/아래로 이동한다.
* 건물의 총 층 수(F), 현재 위치한 층(S), 이동하려는 층(G), U, D
* 5개의 값이 주어질 때, G에 도착하려면 적어도 버튼을 몇 번 눌러야 하는지 구하시오.
* 만약, 엘리베이터를 이용해 G층에 갈 수 없다면 "use the stairs"를 출력한다.*/
public class Graph_5014 {
    //전형적인 그래프 탐색 문제로, 기본적인 풀이 자체는 간단하다.
    //다만 bfs와 dfs 중 어떤 것으로 접근할지를 결정하는 근거에 대해 생각하기 좋은 문제이기에 기록한다.
    //종료조건에 도달하지 못하는 탐색이 있을 수도 있다는 게 이 문제의 특이사항이었다.
    //이 경우, 가지치기를 위해 방문여부를 체크하게 되는데, 여기서 bfs/dfs의 선택이 중요해진다.
    //dfs에서 방문한 층을 가지치기하게 되면 뒤의 탐색에 영향을 미치게 되고, 가지치기된 탐색은
    // 앞선 탐색보다 효율적인(버튼을 누른 횟수가 적은) 탐색일 수 있다.
    // => dfs는 최적의 해를 보장하지 않는다.
    //반면, bfs를 통해 가지치기된 동일한 층에 대한 뒤의 탐색은
    // 적어도 앞서 방문했던 탐색보다 더 비효율적인(버튼을 누른 횟수가 많은) 탐색이다.
    //따라서 이 경우에는 bfs를 선택하는 것이 합리적이다.
   static int F,S,G;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    static int[] move = new int[2];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        F = sc.nextInt();
        S = sc.nextInt();
        G = sc.nextInt();
        move[0] = sc.nextInt();
        move[1] = sc.nextInt();
        visited = new boolean[F + 1];
        bfs(S);
        if(min == Integer.MAX_VALUE)
            System.out.println("use the stairs");
        else System.out.println(min);
    }

    static void bfs(int start) {
        Queue<Integer> que = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        que.add(start);
        map.put(start, 0);

        while (!que.isEmpty()) {
            int cur = que.poll();
            int count = map.get(cur);
            if(cur == G) {
                min = Math.min(min, count);
            }
            int nextUp = cur + move[0];
            int nextDown = cur - move[1];
            if(nextUp <= F && !map.containsKey(nextUp)) {
                que.add(nextUp);
                map.put(nextUp, count + 1);
            }
            if(nextDown >= 1 && !map.containsKey(nextDown)) {
                que.add(nextDown);
                map.put(nextDown, count + 1);
            }
        }
    }
}
