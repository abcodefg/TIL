package programmers;

import java.util.Arrays;

/*n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때,
* 모든 섬이 서로 통행 가능하도록 만들기 위해 필요한 최소 비용을 return 하도록 solution을 완성하세요.
* 다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다.
*
* 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.
*
* (제한사항)
* - 섬의 개수 n은 1 이상 100 이하입니다.
* - costs의 길이는 ((n-1) * n) / 2이하입니다.
* - 임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
* - 같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
* - 모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
*-  연결할 수 없는 섬은 주어지지 않습니다.
 */
public class UnionFind_섬_연결하기 {
    /**
     * 어떤 섬들이 연결되어있는지를 확인할 적절한 방법을 몰라 오래 헤맸던 문제이다.
     * 풀이에 Union-Find가 사용된다는 사실을 힌트 삼아 개념을 알아보고 풀이를 작성했다.
     */
    private int[] parent;
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

        // 어떤 섬 i의 parent가 자신이 되도록 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;

        for (int i = 0; i < costs.length; i++) {
            int islandA = costs[i][0];
            int islandB = costs[i][1];

            if (find(islandA) != find(islandB)) {
                union(islandA, islandB);
                answer += costs[i][2];
            }
        }

        return answer;
    }

    // 두 섬을 연결한다 (= parent를 연결된 섬들 중 index가 가장 작은 것으로 통일시킨다.)
    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y)
            parent[y] = x;
        else
            parent[x] = y;
    }

    // 어떤 섬의 parent를 찾는다.
    private int find(int x) {
        if (x == parent[x]) {
            return parent[x];
        }
        else {
            // TODO 확인할 것 : 재귀 호출의 결과값을 할당하는 동시에 return하는 구조
            return parent[x] = find(parent[x]);
        }
    }
}
