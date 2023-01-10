package programmers;

import java.util.Arrays;

/*과학자의 생산성과 영향력을 나타내는 지표인 H-Index는 다음과 같이 구합니다.
* 어떤 과학자가 발표한 논문 중, h번 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면
* h의 최대값이 이 과학자의 H-Index 입니다.
*
* 어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때,
* 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.*/
public class Sort_H_Index {
    /**
     * H-Index는 결국 주어진 '논문의 개수' 범위 내에서 결정된다는 점만 명심하면
     * 기준을 잡고 풀이를 떠올리기 쉽다.
     */
    public int solution(int[] citations) {
        int answer = 0;
        // 인용 횟수의 배열을 오름차순으로 정렬한다.
        Arrays.sort(citations);

        // 반복할수록 h는 작아지고, citations[i]는 커진다.
        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i; // citation[i]보다 인용 횟수가 크거나 같은 논문의 수

            if (citations[i] >= h) { // h번 이상 인용된 논문이 h편
                answer = h;          // h는 반복할수록 작아지므로 반복문을 종료
                break;
            }
        }
        return answer;
    }
}
