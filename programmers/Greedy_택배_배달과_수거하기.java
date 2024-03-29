package programmers;

/*당신은 일렬로 나열된 n개의 집에 택배를 배달하려 합니다. 배달할 물건은 모두 크기가 같은 재활용 택배 상자에 담아 배달하며, 배달을 다니면서 빈 재활용 택배 상자들을 수거하려 합니다.
* 배달할 택배들은 모두 재활용 택배 상자에 담겨서 물류창고에 보관되어 있고, i번째 집은 물류창고에서 거리 i만큼 떨어져 있습니다.
* 또한 i번째 집은 j번째 집과 거리 j - i만큼 떨어져 있습니다. (1 ≤ i ≤ j ≤ n)
*
* 트럭에는 재활용 택배 상자를 최대 cap개 실을 수 있습니다. 트럭은 배달할 재활용 택배 상자들을 실어 물류창고에서 출발해 각 집에 배달하면서,
* 빈 재활용 택배 상자들을 수거해 물류창고에 내립니다. 각 집마다 배달할 재활용 택배 상자의 개수와 수거할 빈 재활용 택배 상자의 개수를 알고 있을 때, 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 구하려 합니다.
* 각 집에 배달 및 수거할 때, 원하는 개수만큼 택배를 배달 및 수거할 수 있습니다.
*
* 트럭에 실을 수 있는 재활용 택배 상자의 최대 개수를 나타내는 정수 cap, 배달할 집의 개수를 나타내는 정수 n,
* 각 집에 배달할 재활용 택배 상자의 개수를 담은 1차원 정수 배열 deliveries와 각 집에서 수거할 빈 재활용 택배 상자의 개수를 담은 1차원 정수 배열 pickups가 매개변수로 주어집니다.
* 이때, 트럭 하나로 모든 배달과 수거를 마치고 물류창고까지 돌아올 수 있는 최소 이동 거리를 return 하도록 solution 함수를 완성해 주세요.
*
* https://school.programmers.co.kr/learn/courses/30/lessons/150369?language=java
 */
public class Greedy_택배_배달과_수거하기 {
    /**
     * 오가는 이동 거리를 최소화하려면 물류 창고로부터 먼 집부터 우선적으로 배달 혹은 수거를 마쳐야 한다.
     *
     * 반복문을 배열의 뒤에서부터(=먼 집에서부터) 순회하면서 배달/수거할 상자가 남은 집이 있는 경우
     * 해당 집까지의 거리 * 2만큼 answer를 더해나가면 되겠다는 생각이 들었다.
     *
     * 배달이나 수거 어느 한 쪽만을 고려하면 그리 어렵지 않았겠으나,
     * 배달하고 오는 길에 수거하는 경우(혹은 수거하러 가는 길에 배달하는 경우)의 처리 방법을 떠올리기가 까다로웠다.
     *
     * 변수와 조건문을 덕지덕지 추가하면서 이대로 가다간 진전이 없겠다는 생각이 들었고
     * 다른 분의 풀이를 참고했다. (https://oh2279.tistory.com/147)
     *
     * 가장 먼 곳부터 탐색을 시작하되, 배달/수거해야 하는 상자가 하나라도 있으면 그 집으로 이동한다는 접근은 동일하다.
     * 핵심은 각 위치의 배달/수거 값들에서 cap 값을 빼주는 것이다.
     * 어떤 집을 방문하는 매 횟수마다 cap만큼의 택배를 배달/수거할 기회가 생긴다.
     * 이러한 기회 각각을 음수로 표현하고, 방문시의 해당 집의 배달/수거할 택배의 여분을 여기에 더하며,
     * 둘 중 어느 값이라도 양수라면 택배의 여분이 없을 때까지 해당 위치로 이동한다.
     *
     */

    // TODO 확인할 것 (1) : 반환 타입을 반드시 확인하기
    //  이 문제의 경우 습관적으로 int 값을 반환하다가 몇몇 테스트 케이스에서 오답이 떴다.
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int dCount = 0, pCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            dCount += deliveries[i];
            pCount += pickups[i];

            while (dCount > 0 || pCount > 0) {
                // TODO 확인할 것 (2)
                dCount -= cap;
                pCount -= cap;

                answer += 2 * (i + 1);
            }
        }
        return answer;
    }
}
