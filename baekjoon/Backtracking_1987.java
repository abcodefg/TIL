package baekjoon;

import java.util.Scanner;

//#1987 알파벳
/*세로 R칸, 가로 C칸으로 된 보드가 있고 각 칸에 알파뱃 대문자가 적혀 있다.
* 말은 상하좌우 인접한 네 칸 중 하나로 이동할 수 있는데
* 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
* 말이 지날 수 있는 최대의 칸 수를 구하라.*/
public class Backtracking_1987 {
    /*어떤 2차원 배열의 상태를 추적해야 때에는 재귀 dfs가
    * 처음에는 지나온 알파벳을 저장하는 동시에
    * count도 셀 수 있다는 생각에 Set<Character>을 썼다.
    * 정답이긴 했지만 속도와 메모리 측면에서 불만족스러웠다.
    * set이나 list의 요소 접근 속도가 배열에 비해 현저히 떨어진다는 걸 재확인했다.
    * 다음에는 인덱스가 각 알파벳에 대응되는 boolean 배열과 int 매개변수를 사용했고
    * 수행시간는 1/2 이하, 메모리 사용량은 1/15 이하(!)로 떨어졌다*/
    static int R, C, max;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][] board;
    static boolean[] included = new boolean[26];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        board = new char[R][C];
        for(int i = 0; i < R; i++) {
            board[i] = sc.nextLine().toCharArray();
        }

        max = 1;
        included[board[0][0] - 'A'] = true;
        dfs(0,0,1);
        System.out.println(max);
    }

    static void dfs(int r, int c, int count) {
        for(int i = 0; i < 4; i++) {
            int nextR = r + dr[i];
            int nextC = c + dc[i];
            if(nextR >= 0 && nextC >= 0 && nextR < R && nextC < C &&
                    !included[board[nextR][nextC] - 'A']) {
                included[board[nextR][nextC] - 'A'] = true;
                max = Math.max(max, count + 1);

                dfs(nextR, nextC, count + 1);
                included[board[nextR][nextC] - 'A'] = false;
            }
        }
    }
}
