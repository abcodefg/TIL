package programmers.양궁대회;
import java.util.*;

public class Bitmasking_양궁대회 {
    /**
     * 각 요소가 두 가지 상태값(포함/불포함, 승리/패배 등)을 가지는, 순서가 있는 요소들의 집합이 등장한다면
     * 이를 나타내기 위해 비트마스크를 사용하는 것이 효과적이다.
     *
     * 이 문제의 경우 크기에 따른 순서가 있는 각 원의 점수를 '라이언의 승리 / 그 외'로 나누어 나타낼 수 있다.
     *
     * 참고한 풀이 : https://www.youtube.com/watch?v=zwjJ1-PWU1g
     */
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        int[] tmp = new int[11];
        int maxDiff = 0;

        // 라이언이 화살을 더 많이 맞힌 경우 1로 나타내는 식으로 비트마스크로 과녁별 결과를 나타낸다
        // ex) WLWLLLLLLL -> 0000000101(2)
        for (int subset = 1; subset < (1 << 10); ++subset) {    // 경기 결과를 순회한다
            int ryan = 0, apeach = 0, cnt = 0;
            for (int i = 0; i < 10; ++i) {                      // 각 과녁의 결과를 순회한다
                if ((subset & (1 << i)) != 0) {                 // 해당 과녁에서 라이언이 점수를 가져간다면
                    ryan += 10 - i;
                    tmp[i] = info[i] + 1;                       // 어피치보다 1개 더 많이 맞힌 걸로 기록
                    cnt += tmp[i];                              // 라이언이 던진 총 화살의 개수 그만큼 증가
                } else {                                        // 라이언이 점수를 가져가지 않는다면
                    tmp[i] = 0;
                    if (info[i] > 0)                            // 어피치가 1개 이상의 화살을 맞힌 경우에만 어피치의 점수 증가
                        apeach += 10 - i;
                }
            }

            if (cnt > n) continue;                              // 라이언이 던진 화살의 개수가 주어진 n발보다 많다면 스킵

            tmp[10] = n - cnt;                                  // 남는 화살은 점수가 0인 과녁에 맞힌 것
            if (ryan - apeach == maxDiff) {                     // 점수가 동일하다면 가장 낮은 점수를 더 많이 맞힌 경우를 정답으로
                for (int i = 10; i >= 0; --i) {
                    if (tmp[i] > answer[i]) {
                        maxDiff = ryan - apeach;
                        answer = Arrays.copyOf(tmp, tmp.length);
                        break;
                    } else if (tmp[i] < answer[i]) {
                        break;
                    }
                }
            } else if (ryan - apeach > maxDiff) {               // 계산한 점수가 기존의 최대 점수차보다 크다면 그 기록을 정답으로
                maxDiff = ryan - apeach;
                answer = Arrays.copyOf(tmp, tmp.length);
            }
        }

        if (maxDiff == 0)                                       // 라이언의 점수가 어피치의 점수보다 낮거나 같으면 -1 리턴
            return new int[]{-1};

        return answer;
    }
}
