package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//#1744 수 묶기
/*어떤 수열의 합을 구하려고 한다.
* 이때, 위치가 다른 두 수를 묶어서 곱한 값을 더할 수 있다.
* 어떤 수는 한 번만 묶거나 묶지 않아야 한다.
* 수열의 각 수를 적절히 묶었을 때 합의 최대값을 구하라.*/
public class Greedy_1744 {
    // 양수는 1을 제외하고는 절대값이 큰 순서대로 두 수를 묶어야 합이 최대가 된다.
    // 음수 역시 절대값이 큰(값은 작은) 두 수를 묶어야 하며 음수 다음에 0이 온다면 둘을 묶어야 한다.

    // 접근 방식 자체는 복잡하지 않으나
    // 어떤 자료구조를 어떻게 사용해 구현하는지가 중요한 것 같다.
    // 이번엔 풀이에 급급해 배열 대신 리스트를 그것도 두 개나 사용했는데,
    // 리스트는 참조를 위한 메모리를 추가로 할당해야 하므로 바람직한 풀이라고는 볼 수 없다.
    // 검색해보니 입력받은 배열 하나를 활용하는 풀이가 있어서 풀이 하단에 링크를 남기겠다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> pos = new ArrayList<>();
        List<Integer> zeroAndNeg = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0) pos.add(num);
            else zeroAndNeg.add(num);
        }

        pos.sort(Comparator.reverseOrder());
        zeroAndNeg.sort(Comparator.naturalOrder());
        int sum = 0;

        for(int i = 0; i < pos.size(); i++) {
            if (i == pos.size() - 1) {
                sum += pos.get(i);
                break;
            }
            int curr = pos.get(i);
            int next = pos.get(i + 1);
            if (curr > 1 && next > 1) {
                sum += (curr * next);
                i++;
            } else {
                sum += (curr + next);
                i++;
            }
        }

        for (int i = 0; i < zeroAndNeg.size(); i++) {
            if (i == zeroAndNeg.size() - 1) {
                sum += zeroAndNeg.get(i);
                break;
            }
            int curr = zeroAndNeg.get(i);
            int next = zeroAndNeg.get(i + 1);
            sum += (curr * next);
            i++;
        }

        // TODO 확인할 것: 배열 풀이 참고
        //https://log-laboratory.tistory.com/79

        System.out.println(sum);
    }
}
