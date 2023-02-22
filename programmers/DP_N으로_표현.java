package programmers;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/*
* 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.
*
* 12 = 5 + 5 + (5 / 5) + (5 / 5)
* 12 = 55 / 5 + 5 / 5
* 12 = (55 + 5) / 5
*
* 5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
* 이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.
*
* (제한사항)
* - N은 1 이상 9 이하입니다.
* - number는 1 이상 32,000 이하입니다.
* - 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
* - 최솟값이 8보다 크면 -1을 return 합니다.
 */

public class DP_N으로_표현 {
    /**
     * 5가 x개 있을 때 사칙연산으로 표현할 수 있는 숫자를 나타내보자.
     * x = 1 : 5
     * x = 2 : 10 (5+5), 0 (5-5), 25 (5*5), 1 (5/5), 55
     * x = 3인 경우, number가 될 수 있는 숫자는 x = 2인 경우의 number 집합 의 원소와 x = 1인 경우의 number 집합의 원소끼리 사칙연산을 한 숫자 혹은 555라고 할 수 있다.
     *      15 {(5+5)+5}, 5 {(5+5)-5}, ..., 0 {(5/5)/5}, ..., 60 (55+5), ..., 555
     *
     * 특정 값이 규칙적으로 변화하면서 달라지는 연산의 결과값을 구하되, 과거의 결과값을 재활용할 수 있다는 점에서 DP를 활용하면 좋겠다고 생각했다.
     * 그러나 정수값 하나가 아닌 여러 개를 어떻게 기록하고 다음 연산에 활용해야 할 지 감이 잡히지 않아 다른 사람의 풀이를 참고했다.
     *
     */
    public int solution(int N, int number) {
        // 사용할 숫자와 나타내려는 숫자가 같다면 사용 횟수의 최솟값은 1이다.
        // ex) 5를 5를 사용해서 나타낼 때, 5는 한 번만 사용하면 된다.
        if (number == N)
            return 1;

        int answer = -1;

        // 인덱스 i에 숫자 N을 i번 사용한 사칙연산으로 표현할 수 있는 모든 숫자의 집합을 기록한 배열
        // 연산의 결과가 중복되지 않도록 Set을 사용한다.
        List<Set<Integer>> list = new ArrayList<>();

        // List가 아닌 Array를 활용하는 방식도 있었다.
        // 'Set의 배열' 자체가 특이해서 같이 기록한다.
        // 57 - 62번 줄의 코드는 배열을 활용하는 경우 적용되는 풀이이다.
        // Set<Integer>[] arr = new Set[9];

        for (int i = 1; i < 9; i++) {
            list.add(new HashSet<>());
        }
//        int t = N;
//        for (int i = 1; i < 9; i++) {
//             arr[i] = new HashSet<>();
//             arr[i].add(t);
//             t = t * 10 + N;
//        }

        list.get(1).add(N);

        for (int i = 2; i < 9; i++) {
            Set<Integer> set = list.get(i);
            for (int j = 1; j < i; j++) {
                unionSet(set, list.get(j), list.get(i - j));
            }

            set.add(Integer.parseInt(String.valueOf(N).repeat(i)));

            if (set.contains(number)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // 두 SET의 원소들끼리 연산을 진행해 새로운 SET를 생성한다.
    private void unionSet(Set<Integer> union, Set<Integer> preSet, Set<Integer> postSet) {
        for (int a : preSet) {
            for (int b : postSet) {
                union.add(a + b);
                union.add(a - b);
                union.add(b - a);
                union.add(a * b);

                if (b != 0)
                    union.add(a / b);

                if (a != 0)
                    union.add(b / a);
            }
        }
    }
}
