package programmers;

/*카카오톡에서는 이모티콘을 무제한으로 사용할 수 있는 이모티콘 플러스 서비스 가입자 수를 늘리려고 합니다.
* 이를 위해 카카오톡에서는 이모티콘 할인 행사를 하는데, 목표는 다음과 같습니다.
*
* 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것.
* 2. 이모티콘 판매액을 최대한 늘리는 것.
* 1번 목표가 우선이며, 2번 목표가 그 다음입니다.

* 이모티콘 할인 행사는 다음과 같은 방식으로 진행됩니다.

* n명의 카카오톡 사용자들에게 이모티콘 m개를 할인하여 판매합니다.
* 이모티콘마다 할인율은 다를 수 있으며, 할인율은 10%, 20%, 30%, 40% 중 하나로 설정됩니다.
* 카카오톡 사용자들은 다음과 같은 기준을 따라 이모티콘을 사거나, 이모티콘 플러스 서비스에 가입합니다.

* 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매합니다.
* 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상이 된다면, 이모티콘 구매를 모두 취소하고 이모티콘 플러스 서비스에 가입합니다.
*
* 카카오톡 사용자 n명의 구매 기준을 담은 2차원 정수 배열 users, 이모티콘 m개의 정가를 담은 1차원 정수 배열 emoticons가 주어집니다.
* 이때, 행사 목적을 최대한으로 달성했을 때의 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.
*
* (제한사항)
* 1 ≤ users의 길이 = n ≤ 100
* 1 ≤ emoticons의 길이 = m ≤ 7
* https://school.programmers.co.kr/learn/courses/30/lessons/150368
 */
public class BruteForce_이모티콘_할인행사 {
    /**
     * 할인율의 변화에 따른 구매 비용의 변화가 랜덤해서 어떤 규칙을 찾기는 어려웠다.
     * 또한 이모티콘 할인율의 가짓수가 4가지이고 이모티콘의 개수( = 이모티콘 가격 배열의 길이)가 7가지밖에 안 돼서
     * 각 이모티콘에 할인율이 적용되는 4^7 가지 경우를 완전탐색하는 방식으로 접근했다.
     *
     * 이모티콘들의 할인율을 담은 배열을 생성하는 것이 까다로웠는데
     * 이것이 중복순열이라는 것을 깨닫고 관련 코드를 찾아본 뒤 적용했다.
     *
     * 특정 할인율 배열에 대해 '이모티콘 플러스 서비스 가입자 수'와 '이모티콘 판매액'을 구하는 로직은
     * 조건만 잘 정리해서 구현하면 크게 어렵지 않았다.
     *
     * 이후 효율을 높이기 위해 users에 대한 반복문을 인덱스를 명시하는 형식으로 바꾸어서
     * 특정 순회 지점까지의 '서비스 가입자 수(plusMembers) + 가능한 최대 추가 가입자 수'가 현재 maxPlusMembers보다 작다면
     * 반복문을 종료하는 로직을 구현하면 수행시간이 상대적으로 긴 테스트 케이스의 경우 수행시간이 1/3까지도 줄어들일 수 있었다.
     */
    public int maxPlusMembers = 0;
    public int maxTotalSales = 0;
    public int[] discountRate = {40, 30, 20, 10};

    public int[] solution(int[][] users, int[] emoticons) {

        int[] answer = new int[2];
        recur(0, new int[emoticons.length], users, emoticons);
        answer[0] = maxPlusMembers;
        answer[1] = maxTotalSales;
        return answer;
    }
    // TODO 확인할 것 : 재귀를 통한 중복순열 생성
    //  조건문과 내부의 return 구문, 하단의 반복문에 주목하자.
    //  이 문제의 경우 {40,30,20,10}, 4가지의 할인율 중 하나를 선택하여 각 이모티콘에 적용하는 모든 경우의 수를 구해야 하며, 이때 할인율은 중복 선택이 가능하다.
    public void recur(int current, int[] index, int[][] users, int[] emoticons) {
        // 이모티콘의 할인율이 모두 채워졌다면
        if (current == emoticons.length) {
            int plusMembers = 0;
            int totalSales = 0;
//          for (int j = 0; j < users.length; j++) {
            for (int[] user : users) {
//              if (plusMembers + users.length - j < maxPlusMembers)
//                  break;
                int sales = 0;
                // 이모티콘 할인율의 목록을 순회하면서
                for (int i = 0; i < emoticons.length; i++) {
                    // 할인율이 어떤 사용자가 구매를 결심하는 기준보다 낮다면 넘어간다
                    if (index[i] < user[0])
                        continue;
                    // 기준 이상이라면 할인된 가격을 사용자의 구매 비용에 더한다
                    sales += emoticons[i] / 100 * (100 - index[i]);
                }

                // 구매 비용의 합이 이모티콘 플러스 서비스에 가입하는 기준 이상이라면
                if (sales >= user[1])
                    // 서비스 가입자 수 1 증가
                    //  단, 이모티콘 구매를 모두 취소하므로 총 판매액은 늘어나지 않는다
                    plusMembers++;
                // 기준 미만이라면
                else
                    // 구매 비용의 합을 총 판매액에 반영한다
                    totalSales += sales;
            }

            // 서비스 가입자 수가 최대인 경우 중에서 이모티콘 매출액이 최대인 경우를 기록한다
            if (plusMembers == maxPlusMembers) {
                maxTotalSales = Math.max(totalSales, maxTotalSales);
            } else if (plusMembers > maxPlusMembers) {
                maxPlusMembers = plusMembers;
                maxTotalSales = totalSales;
            }

            return;
        }

        // current의 값을 1씩 늘리면서 배열에 각 이모티콘의 할인율을 채워넣는다.
        for (int i = 0; i < 4; i++) {
            index[current] = discountRate[i];
            recur(current + 1, index, users, emoticons);
        }
    }
}
