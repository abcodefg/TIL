package programmers.메뉴_리뉴얼;

import java.util.*;

public class Solution {
    private Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            char[] c = orders[i].toCharArray();
            Arrays.sort(c);
            orders[i] = new String(c);
        }

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();
            int max = 0;

            for (int j = 0; j < orders.length; j++) {
                if (course[i] <= orders[j].length()) {
                    comb(orders[j].toCharArray(), new StringBuilder(), 0, 0, course[i]);
                }
            }

            for (Map.Entry<String, Integer> ent : map.entrySet()) {
                max = Math.max(max, ent.getValue());
            }

            for (Map.Entry<String, Integer> ent : map.entrySet()) {
                if (max >= 2 && ent.getValue() == max) {
                    ans.add(ent.getKey());
                }
            }
        }

        Collections.sort(ans);

        return ans.toArray(new String[ans.size()]);
    }

    private void comb(char[] order, StringBuilder sb, int idx, int cnt, int n) {
        if (cnt == n) {
            String s = sb.toString();
            map.put(s, map.getOrDefault(s, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length; i++) {
            comb(order, sb.append(order[i]), i + 1, cnt + 1, n);
            sb.setLength(sb.length() - 1);
        }
    }
}
