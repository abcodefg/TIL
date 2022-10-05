package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//#2580 스도쿠
/*(9 * 9) 크기의 스도쿠 판을 입력받아
* 스도쿠 규칙대로 빈 칸이 모두 채워진 최종 모습을 출력하라.*/
public class BackTracking_2580 {
    //상태를 추적해야 하는 자료가 2차원 배열이므로 데이터의 크기가 크다.
    //탐색에 따른 자료의 변화를 각각 기록할 필요가 없는 재귀 dfs를 선택하는 것이 합리적이다.
    //또한 리스트에 값이 0인 좌표를 저장하고 그 index를 depth로 삼는다.
    //임의로 1~9까지의 숫자를 넣고 가로, 세로, 작은 정사각형 내에 같은 숫자가 없어야만
    //다음 depth의 재귀를 호출하는 방식으로 탐색한다.
    static List<int[]> zero = new ArrayList<>();
    static boolean foundAnswer = false;
    static int[][] sudoku;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sudoku = new int[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sudoku[i][j] = sc.nextInt();
                if(sudoku[i][j] == 0)
                    zero.add(new int[]{i,j});
            }
        }
        dfs(0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int idx) {
        if(idx == zero.size()) {
            foundAnswer = true;
            return;
        }
        int currI = zero.get(idx)[0];
        int currJ = zero.get(idx)[1];
        for(int num = 1; num <= 9; num++) {
            //백트래킹에 해당하는 부분. 중복된다면 넘어간다.
            if(isOk(currI, currJ, num)) {
                sudoku[currI][currJ] = num;
                dfs(idx + 1);
                //TODO 확인할 것 (1): boolean 값을 통한 탐색 종료
                if(foundAnswer) return;
                sudoku[currI][currJ] = 0;
            }
        }
    }

    static boolean isOk(int currI, int currJ, int num) {
        int proxyI = currI / 3 * 3;
        int proxyJ = currJ / 3 * 3;
        //TODO 확인할 것 (2): 하나의 반복문으로 가로, 세로, 작은 사각형의 중복된 수 체크
        for(int i = 0; i < 9; i++) {
            if(sudoku[currI][i] == num && i != currJ) return false;
            if(sudoku[i][currJ] == num && i != currI) return false;
            //TODO 확인할 것 (3): 현재 좌표와 i를 기준으로 속한 작은 사각형 모든 칸의 좌표 설정
            if(sudoku[proxyI + i/3][proxyJ + i%3] == num &&
                    (proxyI + i/3 != currI || proxyJ + i%3 != currJ)) return false;
        }
        return true;
    }
}
