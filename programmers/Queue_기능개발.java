package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*기능 개선 작업을 수행중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
* 뒤에 있는 기능이 먼저 개발되더라도 앞에 있는 기능이 배포될 때 함께 배포됩니다.
* 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와
* 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때
* 각 배포마다 몇 개의 기능이 배포되는지를 담은 정수 배열을 반환하는 함수를 작성하세요.*/
public class Queue_기능개발 {
    /**
     * 처음 작성했던 풀이.
     *
     * 각 기능을 개발하는 데 필요한 일수를 구해 큐에 채운다.
     * 이후, 큐의 요소들을 순회하면서 한 번에 배포되는 기능들의 개수를 세고 answerList에 채운다.
     * answerList를
     */
    public int[] solution(int[] progresses, int[] speeds) {

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 0; i < progresses.length; i++) {
            double rest = 100 - progresses[i];

            int daysRequired = (int) Math.ceil(rest / speeds[i]);
            que.add(daysRequired);
        }

        List<Integer> answerList = new ArrayList<>();

        int count = 0;
        int head = que.peek();
        while (true) {
            int current = que.poll();

            if (head < current) {
                answerList.add(count);
                head = current;
                count = 1;
            } else {
                count++;
            }

            if (que.isEmpty()) {
                answerList.add(count);
                break;
            }
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    /**
     * 다른 분의 풀이를 참고해 리팩토링한 풀이
     *
     * 생각해보니 큐의 요소를 앞에서부터 순회하는 건 똑같은데
     * 큐를 채우는 과정과 한 번에 배포되는 기능의 수를 세는 과정이 나뉠 필요가 없었다.
     * 큐 맨 앞의 요소보다 작거나 같은 값이 나오면 큐를 채우고,
     * 보다 큰 값이 나오면 큐의 크기(=한 번에 배포되는 기능의 수)를 정답 list에 더하면 되는 것이었다.
     *
     * (1)불필요하게 반복되는 과정이 없는지,
     * (2)앞선 계산에서 확보된 값 중에서 활용할 수 있는 것이 없는지
     * 살펴보는 습관을 들여야겠다.
     */
    public int[] solution_refactored(int[] progresses, int[] speeds) {

        Queue<Integer> que = new ArrayDeque<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            double rest = (100 - progresses[i]);
            int daysRequired = (int) Math.ceil(rest / speeds[i]);

            if (!que.isEmpty() && que.peek() < daysRequired) {
                answerList.add(daysRequired);
                que.clear();
            }

            que.add(daysRequired);
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}
