package programmers.여행_경로;

import java.util.*;

public class Solution {
    /**
     * [정렬한 tickets 배열과 재귀를 활용한 풀이]
     * 출발지가 동일한 항공권들끼리 알파벳 순으로 모이게끔 tickets를 정렬한다.
     *
     * 'ICN'에서 시작해 경로 상의 도시에서 사용할 수 있는 항공권을 순서대로 조회해서 도착지를 path에 추가하고
     * 모든 항공권을 사용하면 재귀 DFS를 종료한다.
     *
     * 매 재귀 호출시에 for 문에서 모든 항공권의 개수(=used.length)만큼 순회해야 하기 때문에 효율적인 풀이는 아니다.
     */
    private boolean found;
    private String ans;
    public String[] solution_recur(String[][] tickets) {
        // 재귀 호출을 빠르게 종료하기 위한 boolean 변수
        found = false;

        // tickets 배열을 출발지 알파벳 순 -> 도착지 알파벳 순으로 정렬한다
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
        // 여행 경로를 담는 리스트(역순으로 채워넣는다)
        List<String> path = new ArrayList<>();

        // 출발지 - 도착지의 인접 리스트를 생성한다
        Map<String, List<String>> graph = new HashMap<>();
        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);
        }

        // 출발지별 도착지의 리스트를 알파벳 역순으로 정렬한다
        for (List<String> destinations : graph.values()) {
            Collections.sort(destinations, Collections.reverseOrder());
        }

        Stack<String> stack = new Stack<>();
        stack.push("ICN");

        while (!stack.isEmpty()) {
            String cur = stack.pop();
            // 현재 도시를 출발지로 하는 항공권이 없거나 다 사용한 경우 경로에 추가한다
            // 해당 도시는 dead end이므로, 그 도시가 Stack에 담기게 해준 항공권을 다른 모든 항공권의 사용이 이루어진 후에 사용한다
            if (!graph.containsKey(cur) || graph.get(cur).isEmpty()) {
                path.add(cur);
            } else {
                stack.push(cur);
                stack.push(graph.get(cur).remove(graph.get(cur).size() - 1));
            }
        }

        Collections.reverse(path);
        return path.toArray(new String[0]);
    }
}
