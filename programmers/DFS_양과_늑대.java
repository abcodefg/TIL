package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
public class DFS_양과_늑대 {
    /**
     *  많은 수의 양을 모아야 하므로 dfs, 늑대의 수가 양의 수보다 크거나 같아질 경우 되돌아가야 하므로 백트래킹을 생각할 수 있다.
     *
     *  그러나, 한 번 방문했던 노드도 다시 방문할 수 있다는 점에서 일반 dfs와 다르며,
     *  따라서 따로 탐색 리스트를 만들어 주어야 한다.
     *
     *  이 문제의 경우 어떤 노드에서 다음에 방문할 수 있는 경로에는 자식 노드 뿐만 아니라 형제의 자식 노드까지 포함된다.
     *  그런데, 어떤 노드에서 형제의 자식 노드에 대한 정보를 알 수 없으므로, 부모 노드로부터 이를 포함한 탐색 리스트를 전달받아야 한다.
     *
     *  리스트에 포함되는 노드는 아직 양 혹은 늑대를 모으지 않은 노드여야 하므로
     *  한 번 탐색이 이루어진 노드는 탐색 리스트에서 제외해야 한다.
     *
     * 참고 : https://g-egg.tistory.com/76
     */
    private List<Integer>[] adj;
    private int[] val;
    private int ans;
    public int solution(int[] info, int[][] edges) {

        adj = new ArrayList[info.length];
        for (int[] edge : edges) {  // 인접 리스트 생성
            int p = edge[0];
            int c = edge[1];

            if (adj[p] == null)
                adj[p] = new ArrayList<>();

            adj[p].add(c);
        }
        val = info;

        dfs(0, 0, 0, List.of(0));

        return ans;
    }

    // TODO Check (1) : 방문할 노드 목록을 관리하는 재귀 dfs
    private void dfs(int i, int sheep, int wolf, List<Integer> list) {
        // TODO Check (2) : XOR 연산 활용
        sheep += val[i] ^ 1;
        wolf += val[i];

        if (wolf >= sheep) return;
        ans = Math.max(ans, sheep);

        List<Integer> next = new ArrayList<>(list); // 다음으로 방문할 노드의 목록 생성
        next.remove(Integer.valueOf(i));            // 목록에서 현재 노드 제거

        if (adj[i] != null) {                       // 현재 노드의 자식 노드들을 방문할 노드 목록에 추가
            next.addAll(adj[i]);
        }

        for (int n : next) {
            dfs(n, sheep, wolf, next);
        }
    }

    /**
     * 같은 dfs 풀이이지만, 다음 두 가지 측면에서 차별화된다.
     *  1) 노드가 아닌 이진트리의 방문 상태를 탐색의 대상으로 삼는다는 점
     *  2) 방문 상태를 나타내고 늑대의 수를 세는 과정을 비트마스크를 활용해 구현했다는 점
     *
     *  이를테면, 5개의 노드 중 0번 노드와 1번 노드만을 방문한 상태는
     *  00011(2) = 3으로 나타낼 수 있다.
     */
    private int n;
    // TODO Check (3) : 이진 트리의 구조를 자식 노드의 배열로 기록
    private int[] l, r;
    private boolean[] visited;
    public int solution2(int[] info, int[][] edges) {
        ans = 1;
        n = info.length;
        l = new int[n];
        r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, -1);
        val = info;
        visited = new boolean[1 << info.length];

        for (int[] edge : edges) {
            int p = edge[0], c = edge[1];

            if (l[p] == -1) l[p] = c;
            else r[p] = c;
        }

        dfs(1);

        return ans;
    }

    private void dfs(int state) {
        if (visited[state]) return;
        visited[state] = true;

        int num = 0, wolf = 0;
        for (int i = 0; i < n; i++) {
            // TODO Check (4) : 비트 마스킹을 활용한 특정 원소 조회
            if ((state & (1<<i)) != 0) {
                num++;
                wolf += val[i];
            }
        }
        if (wolf * 2 >= num) return;
        ans = Math.max(ans, num - wolf);

        for (int i = 0; i < n; i++) {
            if ((state & (1<<i)) == 0) continue;    // 어떤 노드를 방문하지 않은 상태라면 그 자식 노드를 탐색할 수 없으므로 넘어감

            if (l[i] != -1)                         // 각 자식 노드를 방문한 상태에 대해 dfs
                dfs(state | 1<<l[i]);

            if (r[i] != -1)
                dfs(state | 1<<r[i]);
        }
    }
}
