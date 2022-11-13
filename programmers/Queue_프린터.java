package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*어떤 프린터는 다음과 같은 순서로 문서를 인쇄합니다.
* 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 꺼낸다.
* 2. 나머지 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 있으면
*       J를 대기목록의 가장 마지막에 넣는다.
* 3. 그렇지 않으면 J를 인쇄한다.
* 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와
* 내가 인쇄를 요청한 문서의 대기 목록의 어떤 위치에 있는지 알려주는 location이 주어질 때,
* 내가 요청한 문서가 몇 번째 인쇄되는지 return하는 함수를 작성하세요.*/

public class Queue_프린터 {
    /**
     * 수도코드를 작성하며 차분하게 풀이과정을 정리했더니 무난하게 풀 수 있었다.
     *
     * 다음의 과정을 거쳐 답을 구한다.
     *
     * 1. 큐에 문서들의 중요도를 순서대로 넣는다.
     *
     * 2. 정답을 구할 때까지 다음 과정을 반복한다.
     *      큐의 앞에 있는 문서 x를 뽑는다.
     *          x가 현재 가장 중요도가 높은 문서라면
     *              x가 내가 요청한 문서라면
     *                  정답을 (모든 문서의 수 - 큐에 남은 문서의 수)로 설정한다.
     *                  반복문을 종료한다.
     *              중요도가 가장 높은 문서를 변경한다.
     *          x가 가장 중요도가 높은 문서가 아니라면
     *              큐의 마지막으로 문서를 옮긴다.
     *
     *      내가 요청한 문서의 위치를 변경한다.
     */

    public static int solution(int[] priorities, int location) {
        Queue<Integer> que = new LinkedList<>();

        for (int num : priorities) {
            que.add(num);
        }

        Arrays.sort(priorities);

        int maxIdx = priorities.length - 1;
        int answer = 0;
        while (!que.isEmpty()) {
            int curr = que.poll();

            if (curr == priorities[maxIdx]) {
                if (location == 0) {
                    answer = priorities.length - que.size();
                    break;
                }
                maxIdx--;
            } else {
                que.add(curr);
            }

            location = location == 0 ? que.size() - 1 : location - 1;
        }

        return answer;
    }

    /**
     * 다른 분의 풀이 중 참고할 만한 것이 있어 기록한다.
     * 요청한 문서가 인쇄되는 순서(answer)를 활용해
     * 가장 높은 중요도의 인덱스(size - answer)를 나타낸 것이 인상깊다.
     *
     * 또한, 내 풀이에서처럼 answer를 마지막에 구하는 것보다
     * 문서가 출력될 때마다 answer를 1씩 늘리는 게 보다 직관적인 표현인 것 같다.
     */

    public static int solution2(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;

        while(!que.isEmpty()){
            Integer i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--;
                if(l < 0)
                    break;
            }else{
                que.add(i);
                l--;
                if(l<0)
                    l=que.size()-1;
            }
        }

        return answer;
    }
}
