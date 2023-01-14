package programmers;

import java.util.Arrays;

/*배열 array의 i번째 숫자부터 j번째 숫자까지 자르고 정렬했을 때, k번째에 있는 수를 구하려 합니다.
* 예를 들어, array가 [1,5,2,6,3,7,4], i=2, j=5, k=3이라면
* array의 2~5번째까지 자르고 정렬한 배열은 [2,3,5,6]이고 그 3번째 숫자는 5입니다.
* array, [i,j,k]를 원소로 가진 2차원 배열 commands가 매개변수로 주어질 때,
* commands의 모든 원소에 대해 앞서 설명한 연산을 적용했을 때 나온 결과를 배열에 담아 return하도록 solution 함수를 작성해주세요.
 */
public class Sort_K번째수 {
    /**
     * (a)처럼 배열의 일부분을 구하는 자바 api인 Arrays.copyOfRange 메서드를 사용하면 편하긴 하다.
     * 그러나, 풀이를 전적으로 api에 의존하기보단 번거롭더라도 (b)와 같은 방식으로 접근하는 편이 문제해결력을 키우는 데 도움이 될 것이다.
     */
    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];

        //(a)
         for (int i = 0; i < commands.length; i++) {
             int[] command = commands[i];
             int[] sub = Arrays.copyOfRange(array, command[0] - 1, command[1]);
             Arrays.sort(sub);

             answer[i] = sub[command[2] - 1];
         }

        //(b)
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int size = command[1] - command[0] + 1;

            // i와 j가 같다면(부분배열의 사이즈가 1이라면) array의 i번째 요소를 answer에 입력
            if (size == 1) {
                answer[i] = array[command[0] - 1];
                continue;
            }

            // i보다 j가 크다면(부분배열의 크기가 2 이상이라면) 부분배열을 직접 구성해 찾은 요소를 answer에 입력
            int[] sub = new int[size];

            int idx = 0;
            // TODO 확인할 것: 반복문 내부에서 다른 변수도 같이 증가
            //  간단하지만 쓰임새도 많으니 본 김에 형식을 눈에 익혀두고 가자.
            for (int j = command[0] - 1; j < command[1]; j++) {
                sub[idx++] = array[j];
            }

            Arrays.sort(sub);

            answer[i] = sub[command[2] - 1];
        }

        return answer;
    }
}
