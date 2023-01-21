package programmers;

/*Leo는 카펫을 사러 갔다가 아래 그림과 같이 중앙에는 노란색으로 칠해져 있고 테두리 1줄은 갈색으로 칠해져 있는 격자 모양 카펫을 봤습니다.
* Leo가 본 카펫에서 갈색 격자의 수 brown, 노란색 격자의 수 yellow가 매개변수로 주어질 때
* 카펫의 가로, 세로 크기를 순서대로 배열에 담아 return 하도록 solution 함수를 작성해주세요.
* (단, 노란색 격자의 수 yellow는 1 이상 2,000,000이하인 자연수이며,
* 카펫의 가로 길이는 세로 길이보다 길거나 같습니다.)
 */
public class BruteForce_카펫 {
    /**
     * 방정식 문제를 풀듯이 접근하면 수월하게 해결할 수 있다.
     *
     * 다른 사람의 풀이를 살펴보니 아예 연립 이차방정식을 세우고 근의 공식을 활용해 풀기도 하던데
     * 그렇게 풀면 수학 문제 풀이랑 다를 게 없지 않나 싶다.
     */
    public int[] solution(int brown, int yellow) {

        int[] answer = new int[2];

        // 1개 이상의 노란색 격자를 포함할 수 있는 가장 짧은 세로 길이는 3이다.
        int height = 3;
        // 갈색 격자의 개수는 '2 * (가로 길이 + 세로 길이) - (겹치는 4개 격자)'임을 활용해
        // 세로 길이에 대한 가로 길이를 구한다.
        int width = (brown + 4) / 2 - height;

        // 가로, 세로 길이를 활용해 구한 중앙 부분의 넓이가 노란색 격자의 개수와 일치할 때까지
        // 가로 길이는 줄이고 세로 길이는 늘린다.
        while ((width - 2) * (height - 2) != yellow) {
            width--;
            height++;
        }

        answer[0] = width;
        answer[1] = height;
        return answer;
    }
}
