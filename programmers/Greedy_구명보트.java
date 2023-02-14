package programmers;

import java.util.Arrays;

/*무인도에 갇힌 사람들을 구명보트를 이용하여 구출하려고 합니다.
* 구명보트는 작아서 한 번에 최대 2명씩 밖에 탈 수 없고, 무게 제한도 있습니다.
* 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 합니다.
*
* 사람들의 몸무게를 담은 배열 people과 구명보트의 무게 제한 limit가 매개변수로 주어질 때,
* 모든 사람을 구출하기 위해 필요한 구명보트 개수의 최솟값을 return 하도록 solution 함수를 작성해주세요.
*
* (구명보트의 무게 제한은 항상 사람들의 몸무게 중 최댓값보다 크게 주어지므로 사람들을 구출할 수 없는 경우는 없습니다.)
*/
public class Greedy_구명보트 {
    /**
     * 이 문제는 다음과 같은 접근 방식으로 인해 그리디 알고리즘 문제로 분류된다.
     *  각 보트에 가능한 한 많은 사람을 태워서 (locally optimal choice)
     *  결과적으로 구출에 필요한 구명보트의 개수를 최소화 (globally optimal solution)
     *
     * Two Pointer를 활용하여 보트에 타지 않은 사람들 중 가장 무거운 사람과 가장 가벼운 사람을 순회한다.
     * 가장 무거운 사람이 가장 가벼운 사람과 같이 탈 수 있는지 확인하고 아니라면 혼자만 태운다.
     */
    public int solution(int[] people, int limit) {

        Arrays.sort(people);

        int count = 0;
        int lightIdx = 0;
        int heavyIdx = people.length - 1;

        while (lightIdx <= heavyIdx) {
            // 가장 무거운 사람과 가벼운 사람의 무게가 limit 이하라면
            if (people[heavyIdx] + people[lightIdx] <= limit) {
                // 둘을 같은 보트에 태우고 pointer를 다음으로 가벼운 사람으로 넘긴다.
                lightIdx++;
            }

            // 한 보트에 한 명을 태우든 두 명을 태우든
            heavyIdx--;
            count++;
        }

        return count;
    }

    /**
     * 앞의 풀이를 살짝 비틀어서 접근한, 훌륭한 풀이가 있어 기록한다.
     *
     * 이 풀이의 접근 방식은 다음과 같다.
     *
     * 보트에 사람을 한 명만 태운다면 "보트의 개수 = 사람의 수"이고,
     * 보트에 사람을 두 명씩 태울 때마다 필요한 보트의 개수가 사람의 수에서 하나씩 줄어들기 때문에
     * 총 보트의 수는 "사람의 수 - 두 명을 태운 보트의 수"이다.
     *
     * 그런데 가벼운 사람을 가리키는 포인터는 0부터 시작해서 두 사람의 무게의 합이 제한 이내일 때마다 1씩 증가하므로
     * 두 명을 태운 보트의 개수와 동일하다.
     *
     * 그러므로 답은 people.length - i이다.
     */
    public int solution2(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        for (; i < j; --j) {
            if (people[i] + people[j] <= limit)
                ++i;
        }
        return people.length - i;
    }
}
