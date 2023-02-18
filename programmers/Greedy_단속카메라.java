package programmers;

import java.util.Arrays;

/*고속도로를 이동하는 모든 차량이 고속도로를 이용하면서 단속용 카메라를 한 번은 만나도록 카메라를 설치하려고 합니다.
*
* 고속도로를 이동하는 차량의 경로 routes가 매개변수로 주어질 때,
* 모든 차량이 한 번은 단속용 카메라를 만나도록 하려면 최소 몇 대의 카메라를 설치해야 하는지를 return 하도록 solution 함수를 완성하세요.
 */
public class Greedy_단속카메라 {
    /**
     * 차량의 이동 경로들을 진입 지점을 기준으로 오름차순으로 정렬한 routes에
     * 순서대로 차량 A, B, C의 경로인 [As, Ae], [Bs, Be], [Cs, Ce]가 있다고 가정하자 (As <= Bs <= Cs).
     *
     * 이때, 먼저 진입한 차의 경로와 나중에 진입한 차의 경로의 관계는 다음 두 가지 경우로 구분된다.
     * 1) 앞차가 뒷차의 경로 전체 혹은 일부를 포함하는 경우 (Ae >= Bs)
     * 2) 두 차의 경로가 겹치지 않는 경우 (Ae < Bs)
     *
     * 반복문을 순회하며 정렬한 차량의 경로들을 순회하되,
     * 앞뒤 차의 경로가 겹치지 않는 경우에만 카메라가 추가로 필요하므로 count를 늘린다.
     */
    public static int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[0] - o2[0]);

        // end : 겹치는 루트의 종료 지점
        // count : 설치해야 하는 카메라 개수 최솟값 (차량의 대수가 1대 이상이므로 1에서 시작한다.)
       int end = routes[0][1], count = 1;
       for (int i = 1; i < routes.length; i++) {
            // 앞차가 뒷차의 경로 전체 혹은 일부를 포함하는 경우
            if (routes[i][1] < end)
                end = routes[i][1];
            // 두 차의 경로가 겹치지 않는 경우 (Ae < Bs)
            else if (routes[i][0] > end) {
                end = routes[i][1];
                // 새로운 구간을 감시할 카메라가 필요하므로 count를 늘린다.
                count++;
            }
        }

        return count;
    }
}
