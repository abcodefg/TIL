package programmers;

import java.util.PriorityQueue;

/*스코빌 지수가 가장 낮은 음식 두 개를 아래와 같은 규칙에 따라 섞어 새로운 음식을 만들 수 있습니다.
* [섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)]
*
* 모든 음식의 스코빌 지수를 담은 배열 scoville과 K가 주어질 때,
* 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성하세요.
* (단, 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 리턴한다.)
* */
public class Heap_더_맵게 {
    /**
     * 우선순위큐의 개념을 익히고 활용하는 기초 문제이다.
     * 접근법은 어렵지 않아 설명은 풀이에 간략하게 병기했다.
     */
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> lowQ = new PriorityQueue<>();
        for (int x : scoville) {
            lowQ.add(x);
        }

        int count = 0;

        //음식이 하나만 남거나 가장 낮은 스코빌 지수가 K 이상이 될 때까지 아래의 과정을 반복한다.
        while (lowQ.size() > 1 && lowQ.peek() < K) {
            //큐에서 가장 낮은 스코빌 지수 두 개를 추출해 섞고 다시 큐에 넣는다.
            int lowest = lowQ.poll();
            int secondLowest = lowQ.poll();

            lowQ.add(lowest + 2 * secondLowest);
            count++;
        }

        //음식을 더 이상 섞을 수 없음에도 스코빌 지수가 K 미만이라면 -1을 리턴한다.
        if (lowQ.size() == 1 && lowQ.peek() < K)
            return -1;

        return count;
    }
}
