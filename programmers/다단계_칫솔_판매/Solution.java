package programmers.다단계_칫솔_판매;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    static class Seller {
        int profit = 0;
        Seller referral;

        Seller(Seller referral) {
            this.referral = referral;
        }
    }
    Map<String, Seller> map = new HashMap<>();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        // Map에서 추천인의 인스턴스를 찾아 판매원의 인스턴스를 초기화하고 이를 Map에 저장
        for (int i = 0; i < enroll.length; i++) {
            String name = enroll[i];
            Seller s = new Seller(map.getOrDefault(referral[i], null));
            map.put(name, s);
        }

        for (int i = 0; i < seller.length; i++) {
            share(map.get(seller[i]), amount[i] * 100);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).profit;
        }
        return answer;
    }

    // 판매원의 이익을 배분
    private void share(Seller seller, int amount) {
        // 민호(center)에 도달한 경우 종료
        if (seller == null) return;

        // 이익의 10%를 추천인에게 배분하고 나머지를 판매원의 이익금에 추가
        int incentive = amount / 10;
        seller.profit += (amount - incentive);
        share(seller.referral, incentive);
    }
}
