package programmers;
/*어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.
* 예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.
*
* 문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다.
* number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.
*
* 제한 조건
* 1. number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
* 2. k는 1 이상 number의 자릿수 미만인 자연수입니다.
 */
public class Greedy_큰_수_만들기 {
    /**
     * 처음 생각해낸 아래의 풀이는 숫자를 k 개를 순차적으로 제거하는 방식이다.
     *
     * 그러나 이 풀이는 한 개의 케이스에서 시간 초과가 떠서 통과하지 못했다.
     */
    public String solutionFailed(String number, int k) {
        StringBuilder sb = new StringBuilder(number);

        // 반복문 내부의 작업을 k번 수행한다.
        for (int i = 0; i < k; i++) {
            int idx = 0;
            // 바로 다음 자리의 숫자가 더 큰 자리를 찾는다.
            while (idx < number.length() - 1 && sb.charAt(idx) >= sb.charAt(idx + 1)) {
                idx++;
            }
            // 찾았다면 그 자릿수, 못 찾았다면 마지막 자릿수를 제거한다.
            sb.deleteCharAt(idx);
        }

        return sb.toString();
    }

    /**
     * 내가 참고한 풀이는 number의 길이 - k 개의 숫자를 앞에서부터 한 자리씩 찾아나가는 식으로 접근한다.
     *
     * 원래의 순서를 유지한 채로, number의 자릿수로 구성할 수 있는 길이가 (number의 길이 - k)인 수 중
     * 1. 첫 번째 숫자가 될 수 있는 수들 중 가장 큰 수 a를 구한다.
     * 2. a가 첫 번째 숫자일 때 두 번째 숫자가 될 수 있는 수들 중 가장 큰 수 b를 구한다.
     * 3. b가 두 번째 숫자일 때 세 번째 숫자가 될 수 있는 수들 중 가장 큰 수 c를 구한다.
     * ...
     *
     * a, b, c, ... 를 이어붙여서 반환한다.
     */
    public String solution(String number, int k) {
        int length = number.length();
        int start = 0;
        StringBuilder sb = new StringBuilder();

        // 정답의 i번 인덱스의 숫자가 될 수 있는 자릿수 중에서
        for (int i = 0; i < length - k; i++) {
            char max = '0';
            // 가장 큰 수를 찾고 그 수가 위치한 인덱스의 바로 다음 인덱스를 다음 시작점으로 설정한다.
            for (int j = start; j <= i + k; j++) {
                if (max < number.charAt(j)) {
                    max = number.charAt(j);
                    start = j + 1;
                }
            }
            // 발견한 가장 큰 수를 정답에 붙인다.
            sb.append(max);
        }

        return sb.toString();
    }
}


