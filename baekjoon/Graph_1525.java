package baekjoon;

import java.util.*;

//#1525 퍼즐
/*(3*3) 표가 1~8까지의 수와 하나의 빈 칸(0)으로 채워져 있다.
* 이들이 무작위로 배치된 초기 상태가 주어지고
* 인접한 네 칸 중 하나가 비어 있으면 그 칸으로 수를 이동할 수 있다.
* 다음에 주어지는 목표 상태로 만들기 위해 최소 몇 번의 이동이 필요한지 구하시오.
* [[1,2,3],
*  [4,5,6],
*  [7,8,0]]*/
public class Graph_1525 {
    //이제는 얼추 안다고 생각했던 String에 대해 정말 배울 게 많은 문제였다.
    //bfs로 접근하는 게 적합하고, Map으로 중복여부를 체크하는 것까진 생각했으나
    //배열을 String 타입으로 나타내는 건 생각을 못했다.
    //앞으로는 주어진 데이터 타입의 변환 가능성에 대해서 열어두고 생각하는 습관을 길러야겠다.
    static int count = -1;
    static String table = "";
    static String target = "123456780";
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {-0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            table += sc.next();
        }
        bfs(table);
        System.out.println(count);
    }

    static void bfs(String table) {
        Queue<String> que = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        que.add(table);
        map.put(table, 0);

        while(!que.isEmpty()) {
            String current = que.poll();
            int idx = current.indexOf("0");
            int depth = map.get(current);
            int curX = idx % 3;
            int curY = idx / 3;
            if(current.equals(target)) {
                count = depth;
                return;
            }
            //TODO 확인할 것 (1): String 타입의 데이터로 배열의 변화 나타내기
            for(int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if(nextX >= 0 && nextY >= 0 && nextX < 3 && nextY < 3) {
                    int nextIdx = nextX + 3 * nextY;
                    char ch = current.charAt(nextIdx);
                    String next = current.replace(ch, 'x');
                    next = next.replace('0', ch);
                    next = next.replace('x', '0');
                    if(map.containsKey(next)) continue;
                    que.add(next);
                    map.put(next, depth + 1);
                }
            }
        }
    }
}
