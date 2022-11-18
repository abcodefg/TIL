package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Queue_다리를_지나는_트럭 {
    /**
     * 막힌 부분에서 오랫동안 진전이 없어 다른 분의 풀이를 참고했다.
     * 다리 위 트럭들의 무게 총합과 총 시간을 변수,
     * 큐의 길이로 다리 위 트럭들의 수를 세어
     * 총 무게와 트럭들의 수를 기준으로 경우를 분기하는 등
     * 내 풀이와 기본적인 접근법은 동일했는데
     *
     * 반복문이 가리키는 현재의 트럭이 다리에 올라가기 전까지
     * 다리 위 트럭의 무게를 늘리고 줄이는 일련의 과정을 일괄적으로 처리하는 방법이 도통 안 떠올랐다.
     *
     * 정답은 이 풀이에서 볼 수 있듯 for 문 안에 while(true)를 넣는 것이었다.
     * 참 간단한 방법인데 이게 안 떠오르다니 이상한 일이다.
     *
     */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;// 총 걸린 시간
        int sum = 0;// 다리 위 트럭들의 무게
        Queue<Integer> q = new LinkedList<>();

        for(int truck : truck_weights){
            while(true){
                //다리 위에 트럭이 없을 때
                if(q.isEmpty()){
                    q.offer(truck);
                    sum += truck;
                    time++;
                    break;
                }
                //다리의 길이만큼 트럭이 찼을 때,
                else if(q.size() == bridge_length){
                    sum -= q.poll();
                }else{
                    // 현재 트럭 포함 sum 무게가 weight이하일 때
                    if(sum + truck<=weight){
                        q.offer(truck);
                        sum +=truck;
                        time++;
                        break;
                    }
                    else{
                        q.offer(0);
                        time++;
                    }
                }
            }
        }

        return time+bridge_length; // 마지막트럭이 다리길이만큼 지나가는시간 추가
    }
}
