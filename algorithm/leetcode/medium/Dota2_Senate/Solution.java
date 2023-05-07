package algorithm.leetcode.medium.Dota2_Senate;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
    /**
     *  어떤 senator는 상대 party의 senator 중 자신의 다음 순서이면서 자신과 가장 가까운 사람을 ban해야 한다.
     *  그렇지 않으면 그 senator가 자신과 같은 편인 senator를 ban할 수 있기 때문이다.
     *  예를 들어, String senate = RDRDD일 때, 첫 번째에 위치한 radiant senator가 두 번째에 위치한 dire senator를 ban하지 않으면
     *  세 번째에 위치한 Radiant가 ban 당할 수 있다.
     *
     *  그리고 투표는 순환하며 계속 이어지므로, ban을 마친 senator는 다음 순서에 편입되어야 한다.
     *
     *  Time : O(n)
     *  Space : O(n)
     *  Runtime : 13 ms (39.85%)
     *  Memory : 43.5 MB (31.57%)
     */
    public String predictPartyVictory(String senate) {
        Queue<Integer> radiant = new ArrayDeque<>();
        Queue<Integer> dire = new ArrayDeque<>();

        for (int i = 0; i < senate.length(); ++i) {
            if (senate.charAt(i) == 'R')
                radiant.add(i);
            else
                dire.add(i);
        }

        int len = senate.length();
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int r = radiant.poll();
            int d = dire.poll();

            if (r < d) {
                radiant.add(len + r);
            } else {
                dire.add(len + d);
            }
        }

        return radiant.isEmpty() ? "Dire" : "Radiant";
    }
}
