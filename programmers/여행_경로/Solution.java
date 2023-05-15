package programmers.여행_경로;

import java.util.*;

public class Solution {
    /**
     * [정렬한 tickets 배열과 재귀를 활용한 풀이]
     */
    private boolean found;
    private String ans;
    public String[] solution_recur(String[][] tickets) {
        // 재귀 호출을 빠르게 종료하기 위한 boolean 변수
        found = false;

        // tickets 배열을 알파벳 순으로 정렬한다
        Arrays.sort(tickets, (o1, o2)
                -> o1[0].equals(o2[0]) ? o1[1].compareTo(o2[1]) : o1[0].compareTo(o2[0]));

        dfs(0, "ICN", "ICN", new boolean[tickets.length], tickets);

        return ans.split(" ");
    }

    private void dfs(int depth, String cur, String path, boolean[] used, String[][] tickets) {
        // 모든 항공권을 사용했다면
        if (depth == tickets.length) {
            // 정답을 저장하고 탐색을 종료한다
            ans = path;
            found = true;
            return;
        }

        for (int i = 0; i < used.length; i++) {
            // 알파벳순으로 가장 빠른 경로가 완성되었다면 탐색을 종료한다
            if (found) return;
            // 항공권의 출발지가 현재 위치와 같고 항공권을 사용하지 않았다면 도착지에서 다음 탐색을 이어간다
            if (!used[i] && tickets[i][0].equals(cur)) {
                used[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], used, tickets);
                used[i] = false;
            }
        }
    }

    /**
     * [인접 리스트와 스택을 활용한 풀이]
     */
    public String[] solution_stack(String[][] tickets) {
        List<String> path = new ArrayList<>();

        Map<String, List<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);
        }

        for (List<String> destinations : graph.values()) {
            Collections.sort(destinations, Collections.reverseOrder());
        }

        Stack<String> stack = new Stack<>();
        stack.push("ICN");

        while (!stack.isEmpty()) {
            String top = stack.pop();

            if (!graph.containsKey(top) || graph.get(top).isEmpty()) {
                path.add(top);
            } else {
                stack.push(top);
                stack.push(graph.get(top).remove(graph.get(top).size() - 1));
            }
        }

        Collections.reverse(path);
        return path.toArray(new String[0]);
    }
}
