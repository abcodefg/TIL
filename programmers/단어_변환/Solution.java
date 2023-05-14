package programmers.단어_변환;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int solution(String begin, String target, String[] words) {
        int len = begin.length();
        boolean[] visited = new boolean[words.length];
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        int cnt = 0;

        while (!q.isEmpty()) {
            // 같은 수의 단계를 거쳐 변환할 수 있는 단어들을 묶어서 처리한다
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                // target과 같다면 거쳐온 단계의 수(cnt)를 반환한다
                if (cur.equals(target)) return cnt;

                for (int j = 0; j < words.length; j++) {
                    // 이전에 변환했던 단어라면 넘어간다
                        // 변환할 수 있는 단어라고 해도 이전보다 많은 단계를 거쳐야 할 것이므로 가장 짧은 변환 과정에 해당하지 않는다
                        // 또한, 이전에 변환했는지 여부를 확인하지 않으면 한 글자만 다른 두 단어가 서로 거듭 변환하며 무한히 순환할 수 있다
                    if (visited[j]) continue;
                    String next = words[j];

                    if (transformable(cur, next)) {
                        q.add(next);
                        visited[j] = true;
                    }
                }
            }

            cnt++;
        }

        return 0;
    }

    private static boolean transformable(String cur, String next) {
        int cnt = 0;

        for (int i = 0; i < cur.length(); i++) {
            // 두 단어에서 다른 문자의 개수를 센다
            if (cur.charAt(i) != next.charAt(i)) {
                cnt++;
                // 다른 문자의 개수가 1보다 크다면 변환할 수 없으므로 false를 반환한다
                if (cnt > 1) return false;
            }
        }

        // 중복되는 단어는 없으므로(cnt != 0), 여기서 cnt는 1일 것이다
        return true;
    }
}
