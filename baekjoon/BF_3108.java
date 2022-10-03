package baekjoon;

import java.util.Scanner;

//#3108 로고
/*좌표평면 위에서 거북이 로봇을 움직여 n개의 직사각형을 그리려고 한다.
* 로봇은 직진하거나 회전할 수 있다.
* 또한, 입에 물고있는 연필을 내리면 선을 그리고 올리면
*
*/
public class BF_3108 {
    //여러모로 아쉬움이 많이 남는 문제다.
    //문제를 이해하고 접근법을 정하거나 풀이의 전반적인 구조를 잡는 건 잘했으나,
    //자잘한 실수들이 있어 여러차례 풀이를 수정해야 했다.
    //호출한 함수(외부)에서 탐색여부를 체크하고 탐색하지 않은 요소를 거듭 주입하는 형태의
    //탐색 메서드는 DFS / BFS의 선택을 유연하게 가져가도 괜찮다는 것을 배웠다.
    //이번 풀이에서는 원래 사용했던 큐 BFS를 재귀 DFS로 변경하였다.
    static int n, count;
    static boolean[] visited;
    static int[][] rects;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        count = 0;
        visited = new boolean[n];
        rects = new int[n][4];

        boolean containsZero = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                rects[i][j] = sc.nextInt();
            }
            //TODO 확인할 것 (1): 원점을 포함하는지 확인하는 조건문
            // 특히 'x1 * x2 <= 0' 표현에 주목
            if (((rects[i][0] == 0 || rects[i][2] == 0) && rects[i][1] * rects[i][3] <= 0) ||
                    ((rects[i][1] == 0 || rects[i][3] == 0) && rects[i][0] * rects[i][2] <= 0)) {
                containsZero = true;
            }
        }
        if (containsZero) count--;

        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i);
                count++;
            }
        }

        System.out.println(count);
    }
    //TODO 확인할 것 (2): 호출한 함수에서 방문여부를 체크하는 재귀 dfs
    // 큐 BFS나 스택 DFS에 비해 훨씬 간략한 풀이가 가능하다.
    static void dfs(int start) {
        visited[start] = true;

        for(int i = 0; i < n; i++) {
            if(!visited[i] && isOverlapped(rects[start], rects[i])) {
                dfs(i);
            }
        }
    }

    static boolean isOverlapped(int[] a, int[] b) {
        //TODO 확인할 것 (3): 두 사각형이 겹치는지 확인하는 메서드
        // 6가지 경우의 수가 있음을 파악하고 이를 '모두' 확인해야 한다.
        if (a[0] > b[2] || a[2] < b[0] || a[1] > b[3] || a[3] < b[1] ||
                (a[0] > b[0] && a[1] > b[1] && a[2] < b[2] && a[3] < b[3]) ||
                (a[0] < b[0] && a[1] < b[1] && a[2] > b[2] && a[3] > b[3])) {
            return false;
        }
        return true;
    }
}
