package programmers;

/*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92344
 */
public class PrefixSum_파괴되지_않은_건물 {
    /**
     * board의 행과 열의 길이가 1000 이하이고, skills의 행의 길이가 250,000이다.
     * board의 지정된 범위를 순회하며 일일이 값을 기록하는 식으로 구현할 경우,
     * 최악의 경우 1000 * 1000 * 250,000 = 2천5백억 회의 연산을 수행해야 하므로 시간초과가 발생한다.
     *
     * 따라서 아래와 같은 방식으로 각 칸의 변화량들을 간결하게 기록할 필요가 있다.
     * 어떤 칸 (r1, c1)에 기록된 변화량은 (r1, c1)부터 맨 끝 칸까지의 범위 안에 있는 모든 칸에 대한 변화량으로 간주한다.
     * 이 경우, 아래 그림에서 왼쪽의 변화량은 오른쪽과 같은 방식으로 표기할 수 있다.
     * 0000                 0000
     * 0110     ===>        010-1
     * 0000                 0-101
     */
    public int solution(int[][] board, int[][] skill) {
        // 변화량을 기록하는 배열
        int[][] changes = new int[board.length + 2][board[0].length + 2];
        for (int[] s : skill) {
            // type이 1일 경우 변화량은 -degree, 2일 경우 +degree
            int change = s[0] == 1 ? -s[5] : s[5];
            int r1 = s[1] + 1, c1 = s[2] + 1, r2 = s[3] + 1, c2 = s[4] + 1;

            changes[r1][c1] += change;
            changes[r1][c2+1] -= change;
            changes[r2+1][c1] -= change;
            changes[r2+1][c2+1] += change;
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 각 칸의 원래 변화량을 구한다
                changes[i+1][j+1] += changes[i][j+1] + changes[i+1][j] - changes[i][j];
                // 현재 내구도에 변화량을 적용한 값이 0보다 크다면 count
                if (board[i][j] + changes[i+1][j+1] > 0)
                    count++;
            }
        }

        return count;
    }
}
