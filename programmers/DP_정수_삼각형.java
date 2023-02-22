package programmers;

import java.util.Arrays;
/*    7
*    3 8
*   8 1 0
*  2 7 4 4
* 4 5 2 6 5
*
* 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
* 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다. 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
* 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때, 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
*
* (제한사항)
* 삼각형의 높이는 1 이상 500 이하입니다.
* 삼각형을 이루고 있는 숫자는 0 이상 9,999 이하의 정수입니다.
 */
public class DP_정수_삼각형 {
    /**
     * 점진적으로 변화하는 값을 따라 동일한 구조의 연산을 거듭하되, 직전 연산의 결과값을 재활용하는 전형적인 DP 문제이다.
     * 다만 이번 문제의 경우, 연산의 결과값을 기록하기 위한 별도의 배열을 선언하기보다는 주어진 배열 triangle을 활용하는 편이 효율적이므로
     * 따로 memoization 을 위한 별도의 배열을 활용하는 TOP-DOWN 방식이 아닌 BOTTOP-UP 방식으로 문제를 풀었다.
     *
     * 다른 분의 풀이 중에서 내 풀이와 동일하게 접근했으나 구조의 측면에서 개선된 버전이 있어서 기록했다.
     */
    public int solution(int[][] triangle) {
        int answer = 0;

        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                // 맨 왼쪽 숫자를 포함한 최대값은 바로 오른쪽 위 숫자를 포함한 최대값 + 현재 값
                if (j == 0)
                    triangle[i][j] += triangle[i - 1][j];
                // 맨 오른쪽 숫자를 포함한 최대값은 바로 왼쪽 위 숫자를 포함한 최대값 + 현재 값
                else if (j == triangle[i].length - 1)
                    triangle[i][j] += triangle[i - 1][j - 1];
                // 나머지 숫자를 포함한 최대값은 왼쪽 위 숫자를 포함한 최대값과 오른쪽 위 숫자를 포함한 최대값 중 더 큰 값 + 현재 값
                else
                    triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }

            // 최종 경로 중 가장 큰 값을 반환
            if (i == triangle.length - 1) {
                for (int j = 0; j < triangle[i].length; j++) {
                    answer = Math.max(answer, triangle[i][j]);
                }
            }
        }

        return answer;
    }

    public int solution2(int[][] triangle) {
        for (int i = 1; i < triangle.length; i++) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; j++)
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
        }

        return Arrays.stream(triangle[triangle.length-1]).max().getAsInt();
    }
}
