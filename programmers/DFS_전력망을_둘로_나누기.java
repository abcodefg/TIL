package programmers;

import java.util.Stack;

/*n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다.
* 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다.
* 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
* 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다.
* 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때,
* 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.
*
* (단, n은 2 이상 100 이하인 자연수이며, wires는 길이가 n-1인 정수형 2차원 배열입니다.)
 */
public class DFS_전력망을_둘로_나누기 {
    int[][] link;
    int answer;

    public int solution(int n, int[][] wires) {
        answer = 100;
        link = new int[n + 1][n + 1];

        for (int[] wire : wires) {
            int a = wire[0], b = wire[1];
            link[a][b] = link[b][a] = 1;
        }

        for (int[] wire : wires) { // 전선 정보를 순회한다.
            int a = wire[0], b = wire[1];

            link[a][b] = link[b][a] = 0; // 두 노드의 연결을 임시로 끊는다.
            dfs(n, a);
            link[a][b] = link[b][a] = 1;
        }
        return answer;
    }

    void dfs(int n, int number){
        boolean[] visited = new boolean[n + 1];
        Stack<Integer> stack = new Stack<>();
        int count = 0;

        stack.push(number);

        while(!stack.isEmpty()) {
            int curr = stack.pop();
            count++; // 탐색한 노드의 개수를 1 증가시킨다.

            if (!visited[curr]) {
                visited[curr] = true;

                for (int i = 1; i <= n; i++) { // 노드들을 순회하며
                    if (link[curr][i] == 1 && !visited[i]) { // 현재 노드와 연결되어 있고 방문한 적이 없다면
                        stack.push(i);
                    }
                }
            }
        }

        // 두 전력망의 송전탑 개수 차이를 구하고 최솟값을 최신화한다.
        int diff = Math.abs(count * 2 - n);

        answer = Math.min(answer, diff);
    }

    /**
     * 다른 분의 풀이 중에서 주어진 자료구조가 트리라는 점을 훌륭하게 활용한 풀이가 있어서 기록한다.
     *
     * 1) dfs를 통해 탐색하는 각 노드에서 자식 노드들의 개수를 재귀적으로 구한다.
     * 2) 자식 노드의 개수를 활용해 해당 노드와 자식 노드의 연결을 끊었을 때
     *    분할된 두 전력망의 송전탑 개수 차이를 구하고 최솟값을 최신화한다.
     */
    int N, min;
    int[][] map;
    int[] vst;
    int dfs(int n){
        vst[n] = 1;
        int child = 1;
        for(int i = 1; i <= N; i++) {
            if(vst[i] == 0 && map[n][i] == 1) {
                child += dfs(i);
            }
        }
        min = Math.min(min, Math.abs(child - (N - child)));
        return child;
    }
    public int solution2(int n, int[][] wires) {
        N = n;
        min = n;
        map = new int[n+1][n+1];
        vst = new int[n+1];
        for(int[] wire : wires) {
            int a = wire[0], b = wire[1];
            map[a][b] = map[b][a] = 1;
        }
        // TODO 확인할 것: 트리의 모든 정점이 루트 노드로 사용될 수 있다.
        dfs(1);
        return min;
    }
}
