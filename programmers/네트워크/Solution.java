package programmers.네트워크;

import java.util.Stack;

public class Solution {
    private boolean[] visited;
    public int solution(int n, int[][] computers) {

        visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            // 이미 방문한 컴퓨터라면 넘어간다
            if (visited[i]) continue;
            // 연결된 모든 컴퓨터를 방문 표시한 뒤 네트워크의 개수를 1 늘린다
            dfs_stack(i, computers);
            answer++;
        }

        return answer;
    }

    // 스택 dfs
    private void dfs_stack(int i, int[][] computers) {
        Stack<Integer> stack = new Stack<>();
        stack.push(i);

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int next = 0; next < computers.length; next++) {
                // 이미 방문했거나 연결되지 않은 컴퓨터라면 넘어간다
                if (visited[next] || computers[cur][next] == 0) continue;
                visited[next] = true;
                stack.push(next);
            }
        }
    }

    // 재귀 dfs
    private void dfs_recur(int cur, int[][] computers) {
        visited[cur] = true;

        for (int next = 0; next < computers.length; next++) {
            if (visited[next] || computers[cur][next] == 0) continue;
            dfs_recur(next, computers);
        }
    }
}
