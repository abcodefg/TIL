package programmers.사라지는_발판;

public class Solution_BruteForce {
    private int rlen, clen;
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    // 결국 두 플레이어의 전략은 동일하므로 동일한 함수를 사용하되 매개변수의 값만 변경한다
    private int dfs(int[][] board, int curX, int curY, int opX, int opY) {
        // 해당 위치로 이동할 수 없는 경우
        if (board[curX][curY] == 0) return 0;
        // 리턴할 최종 값을 저장할 변수
        int res = 0;
        board[curX][curY] = 0;
        for (int i = 0; i < 4; i++) {
            int newX = curX + dr[i];
            int newY = curY + dc[i];
            // 이동하려는 위치가 보드 바깥이거나 이미 방문한 곳이거나 발판이 없는 경우 넘어간다
            if (newX < 0 || newY < 0 || newX >= rlen || newY >= clen) continue;
            if (board[newX][newY] == 0) continue;

            // 이동 횟수를 1 증가시킨다
            int val = dfs(board, opX, opY, newX, newY) + 1;

            // 기존 대전 결과가 패배이고 현재 대전 결과가 승리인 경우
            // 반환값을 변경한다
            if (res % 2 == 0 && val % 2 == 1)
                res = val;
            // 기존 대전 결과가 패배이고 현재 대전 결과도 패배인 경우
            // 최대한 오래 버텨야 하므로 더 큰 값으로 변경한다
            else if (res % 2 == 0 && val % 2 == 0)
                res = Math.max(res, val);
            // 기존 대전 결과가 승리이고 현재 대전 결과도 승리인 경우
            // 최대한 빨리 승리해야 하므로 더 작은 값으로 변경한다
            else if (res % 2 == 1 && val % 2 == 1)
                res = Math.min(res, val);
        }
        board[curX][curY] = 1;
        return res;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        rlen = board.length;
        clen = board[0].length;
        return dfs(board, aloc[0], aloc[1], bloc[0], bloc[1]);
    }
}
