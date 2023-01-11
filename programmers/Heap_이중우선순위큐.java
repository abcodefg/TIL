package programmers;

import java.util.Collections;
import java.util.PriorityQueue;

/*이중 우선순위 큐는 주어진 숫자를 삽입(I 숫자)하거나
* 큐에서 최대값을 삭제(D 1)하거나 최소값을 삭제(D -1)할 수 있는 자료구조를 말합니다.
* 이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0, 0]
* 비어있지 않다면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.*/
public class Heap_이중우선순위큐 {
    /**
     * 우선순위 큐를 두 개 사용하는 것이나 그 외의 기본적인 접근법은 어렵지 않게 떠올릴 수 있었으나
     * 최댓값 / 최솟값을 삭제할 때 각각 최소힙 / 최대힙에서 어떻게 삭제가 이루어져야 하는지 감이 잡히지 않았다.
     * 다른 분의 풀이를 참고하니 자바의 PriorityQueue에는 인자로 입력받은 값을 큐에서 제거하는 remove() 메서드가 있었다.
     */

    public int[] solution(String[] operations) {
        int[] answer = {0, 0};

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation : operations) {
            String[] opArray = operation.split(" ");
            int num = Integer.parseInt(opArray[1]);

            if (opArray[0].equals("I")) { // 삽입 연산 시 최소힙과 최대힙에에 숫자 삽입
                minQ.add(num);
                maxQ.add(num);
            } else if (maxQ.size() > 0){ // 삭제 연산 시(이중 우선순위 큐가 비어있지 않은 상태)
                if (num == 1) { // 최댓값 삭제
                    int max = maxQ.peek();
                    minQ.remove(max);
                    maxQ.remove(max);
                }
                else { // 최솟값 삭제
                    int min = minQ.peek();
                    minQ.remove(min);
                    maxQ.remove(min);
                }
            }
        }

        if (maxQ.size() != 0) { // 큐가 비어있지 않다면 answer에 최댓값과 최솟값을 입력
            answer[0] = maxQ.poll();
            answer[1] = minQ.poll();
        }

        return answer;
    }
}
