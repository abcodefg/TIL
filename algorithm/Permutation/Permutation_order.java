package algorithm.Permutation;

import java.util.ArrayList;

// 1~N까지의 서로 다른 자연수로 이루어진 크기 N의 배열 K를 입력받아
// K가 그 자연수들로 구성할 수 있는 수열 중 몇 번째 경우의 수인지 구하는 문제

public class Permutation_order {
    public int orderOfPermutation(int N, int[] K) {
        // N이 3일 때, 순열의 경우의 수는 다음과 같다.
        // ex) N = 3 -> [[1,2,3],[1,3,2],[2,1,3],[2,3,1],.....]
        // 입력받은 K가 [3,1,2]일 때, K의 차례가 오기 위해서는 우선 1로 시작하는 모든 수열들을 지나야 한다.
        // 1로 시작하는 수열들의 총 개수는 1을 제외한 나머지 수들을 나열하는 경우의 수와 같으므로 2!이다.
        // 다음으로 2로 시작하는 수열들도 지나야 하는데, 그 개수 역시 2!이다.
        // 첫 요소가 일치하고 나면 그 다음 요소가 일치할 때까지 앞의 과정을 반복한다.
        int[] factorial = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        // TODO 확인할 것 (1): 팩토리얼 배열
        factorial[0] = 1;
        for (int i = 1; i < N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        // TODO 확인할 것 (2): 모든 경우의 수 중 특정 순열의 순서 구하기
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        int order = 0;
        for (int i = 0; i < N; i++) {
            int current = K[i];
            for (int j = 0; j < list.size(); j++) {
                if (current == list.get(j)) {
                    list.remove(j);
                    break;
                } else {
                    order += factorial[list.size() - 1];
                }
            }
        }
        return order;
    }
}