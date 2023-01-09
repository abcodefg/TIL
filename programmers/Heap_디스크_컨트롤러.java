package programmers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다.
* 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원원배열 jobs가 매개변수로 주어질 때,
* 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면
* 평균이 얼마나 되는지 return 하도록 solution 함수를 작성해주세요.
*
* 문제 설명 링크 참고: https://school.programmers.co.kr/learn/courses/30/lessons/42627
 */
public class Heap_디스크_컨트롤러 {
    public int solution(int[][] jobs) {
        // 작업이 요청되는 시점 순으로 오름차순으로 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 소요시간이 짧은 작업이 우선순위를 갖는 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
//      아래의 (a) 혹은 (b), (c)와 같이 표기할 수도 있다.
//  (a) PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
//        public int compare(int[] o1, int[] o2) {
//          return o1[1] - o2[1];
//        }
//      });

//  (b) PriorityQueue<int[]> pq = new PriorityQueue<>((int o1, int o2) -> {
//          return o1[1] - o2[1];
//      });

//  (c) PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int idx = 0; // 작업 배열 인덱스
        int end = 0; // 작업 종료 시점
        int count = 0; // 처리 완료된 작업 개수

        int sum = 0; // 작업의 요청부터 종료까지 걸린 시간의 총합

        while (count < jobs.length) {

            // 직전 작업이 종료된 시점(현 시점) 이전에 요청이 이루어진 작업들을 우선순위 큐에 등록
            while (idx < jobs.length && end >= jobs[idx][0]) {
                pq.add(jobs[idx++]);
            }

            if (pq.isEmpty()) { // 직전 작업의 종료 이전에 요청이 이루어진 작업이 없는 경우
                end = jobs[idx][0]; // 다음 작업 요청 시점으로 갱신
            } else { // 직전 작업의 종료 이전에 요청이 이루어진 작업이 있는 경우
                int[] cur = pq.poll(); // 소요시간이 가장 짧은 작업을 다음으로 처리
                sum += end + cur[1] - cur[0]; // 해당 작업의 요청부터 종료까지 소요 시간을 추가
                end += cur[1]; // 작업 종료 시점 갱신
                count++;
            }
        }

        return sum / jobs.length;
    }
}
