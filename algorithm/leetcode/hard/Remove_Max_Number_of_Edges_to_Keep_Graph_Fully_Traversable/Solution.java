package algorithm.leetcode.hard.Remove_Max_Number_of_Edges_to_Keep_Graph_Fully_Traversable;

import java.util.Arrays;

public class Solution {
    /**
     * [Union Find 풀이]
     *
     * 한참을 헤매다 결국 다른 분의 풀이를 참고했다.
     * 1) 불필요한 edge를 제거해 나가는 것이 아니라 우선적으로 필요한 edge를 선택하면서 graph를 구성해나가는 식으로 풀 수 있다는 점과
     * 2) Union Find로 풀 수 있다는 점을 알아차리고
     * 3) Path compression, Union by rank 와 같은 Union Find 최적화 기법을 적용한 클래스를 구현하는 게 까다로운 문제였다.
     *
     * Time : O(edges.length * α(n)) (α(n)에 대한 설명은 하단의 '참고' 참조)
     * Space : O(n)
     * Runtime : 11 ms (Beats 98.6%)
     * Memory : 104.8 MB (Beats 29.93%)
     *
     * [참고]
     * - Union Find 최적화
     * https://hazel-developer.tistory.com/272?category=816058
     *
     * - Ackermann 역함수 (= α(n))
     * https://www.geeksforgeeks.org/inverse-ackermann-function/
     */

    // Union Find 자료구조
        // 각 메서드의 시간 복잡도는 아래와 같다
        // Constructor  /  Find  /  Union  /  Connected
        //    O(n)         α(n)      α(n)       α(n)
    static class UF {
        // 나뉘어 있는 트리의 개수
        // 서로 다른 두 트리(노드의 묶음)가 병합(union)되면서 1씩 줄어든다
        int components;

        // 각 노드가 속한 트리의 루트 노드를 나타내는 배열
        int[] root;

        // Union by Rank를 적용하기 위한 배열
        // 각 트리의 루트 노드에 해당하는 인덱스에 해당 트리의 높이(height)를 기록한다
        int[] rank;

        public UF(int n) {
            components = n;

            // 각 노드의 루트 노드는 자기 자신으로 초기화한다
            // ex) {0, 1, 2, 3, ..., n}
            root = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                root[i] = i;
            }

            // 각 트리의 rank는 1로 초기화한다
            rank = new int[n + 1];
            Arrays.fill(rank, 1);
        }

        public boolean union(int x, int y) {
            // 두 노드의 루트 노드를 찾는다
            x = find(x);
            y = find(y);

            // 두 노드의 루트 노드가 같다면 이미 연결되어 있으므로 false를 반환한다
            if (x == y) {
                return false;
            }
            // 두 노드의 루트 노드가 다르다면
            else {
                // 각 노드가 속한 두 개의 트리 중에서 높이가 더 큰(rank가 큰) 트리에 다른 트리를 합친다
                // 이 경우 합친 트리의 높이는 변하지 않는다
                if (rank[x] > rank[y]) {
                    root[y] = x;
                } else if (rank[y] > rank[x]) {
                    root[x] = y;
                } else {
                    // 두 트리의 높이가 같다면 임의로 x가 속한 트리에 y의 트리를 합친다
                    // 이 경우 합친 트리의 높이는 기존 높이보다 1 증가한다
                    rank[x] += 1;
                    root[y] = x;
                }

                // 두 개의 트리를 합쳤으므로 component의 개수가 1 감소한다
                components--;
                return true;
            }
        }

        // Path Compression(경로 압축)을 적용한 find
        // 각 노드가 자신의 부모 노드가 아닌 루트 노드를 가리키게 한다
        private int find(int x) {
            if (x == root[x])
                return x;

            return root[x] = find(root[x]);
        }

        // 모든 노드가 연결되었다면 components는 1 이하일 것이다
        public boolean isConnected() {
            return this.components <= 1;
        }
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UF Alice = new UF(n);
        UF Bob = new UF(n);

        // 필요한 edge의 개수
            // 제거할 수 있는 edge 개수의 최대값 = 전체 edge 개수 - graph를 완전히 연결하기 위해 필요한 edge 개수의 최솟값
        int cnt = 0;

        // Alice와 Bob이 모두 통행할 수 있는 Type 3 간선이 Type 1,2 보다 우선적으로 필요하다
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                int u = edge[1], v = edge[2];
                // Type 3 간선으로 Cycle이 형성되지 않는 경우에만 cnt 증가
                if (Alice.union(u, v)) {
                    Bob.union(u, v);
                    cnt += 1;
                }
            }
        }

        // Type 1, 2 간선 중에서 이미 연결된 정점들을 연결하지 않는 간선만 선택(cnt 증가)
        for (int[] edge : edges) {
            int u = edge[1], v = edge[2];
            if (edge[0] == 1) {
                if (Alice.union(u, v)) cnt += 1;
            } else if (edge[0] == 2) {
                if (Bob.union(u, v)) cnt += 1;
            }
        }

        // Alice와 Bob 양쪽 모두 graph 상의 모든 정점이 연결된 경우에만 정답 반환
        if (Alice.isConnected() && Bob.isConnected()) {
            return edges.length - cnt;
        } else {
            return -1;
        }
    }
}
