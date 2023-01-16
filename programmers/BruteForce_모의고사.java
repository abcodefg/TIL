package programmers;

import java.util.ArrayList;
import java.util.List;

/*수포자 삼인방은 아래와 같은 방식으로 모의고사에 수학 문제를 전부 찍으려 합니다.
* 1번 수포자: 1,2,3,4,5,1,2,3,4,5,...
* 2번 수포자: 2,1,2,3,2,4,2,5,2,1,2,3,2,4,2,5,...
* 3번 수포자: 3,3,1,1,2,2,4,4,5,5,3,3,1,1,2,2,4,4,5,5,...
 */
public class BruteForce_모의고사 {
    /**
     * 각 수포자가 찍은 답을 인덱스를 활용한 식으로 나타내려고 하다가 시간을 많이 잡아먹었다.
     * 설령 그렇게 적더라도 코드의 가독성이 심각하게 떨어지니 그냥 주어진 값을 활용하도록 하자.
     */
    public int[] solution(int[] answers) {
        // 1,2,3 번 수포자의 점수를 나타내는 배열
        // TODO 확인할 것: 배열 대신 3개의 변수를 활용하는 편이 수행 시간이 단축된다.
        //  아래의 반복문에서 포인트를 증가시킬 때마다 배열을 탐색하지 않아도 되기 때문이다.
        int[] points = new int[3];

        int[] studentA = {1, 2, 3, 4, 5};
        int[] studentB = {2,1,2,3,2,4,2,5};
        int[] studentC = {3,3,1,1,2,2,4,4,5,5};

        // 각 수포자가 찍는 패턴의 주기에 맞춰 인덱스를 설정한다.
        // 찍은 값이 정답이라면 학생의 점수를 1 증가시킨다.
        for (int i = 0; i < answers.length; i++){
            if (answers[i] == studentA[i % 5])
                points[0]++;

            if (answers[i] == studentB[i % 8])
                points[1]++;

            if (answers[i] == studentC[i % 10])
                points[2]++;
        }

        // 학생의 점수들 중 최대값을 구한다.
        int max = Math.max(points[0], Math.max(points[1], points[2]));

        // 가장 높은 점수를 얻은 학생들을 배열에 추가해 반환한다.
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == max)
                list.add(i + 1);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
