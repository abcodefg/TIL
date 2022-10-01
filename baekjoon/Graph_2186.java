package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//#2186 문자판
/*각 칸에 알파벳 대문자가 적힌 N * M 크기의 문자판이 주어진다.
* 이 문자판의 아무 칸에서 시작하여 상하좌우로 K개의 칸을 움직이며
* 문자들을 차례대로 모아 단어를 만들 수 있다.
* */
public class Graph_2186 {
    static int N, M, K;
    static String target;
    static int[] dn = {-1, 1, 0, 0};
    static int[] dm = {0, 0, -1, 1};
    static char[][] arr;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j <M; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        target = br.readLine();
        //TODO 확인할 것 (1): 3차원 배열 메모이제이션
        // 어떤 알파벳이 단어의 몇번째에 오는지도 고려해야 한다.
        dp = new int[N][M][target.length()];

        //TODO 확인할 것 (2): -1로 배열 채우기
        // 틀렸던 풀이에선 생성시 초기 상태(=0)으로 설정해
        // 방문한 문자들 중 단어에 들어가는 것만 체크하는 실수를 했다.
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(arr[i][j] == target.charAt(0)) {
                    answer += dfs(i, j, 1);
                }
            }
        }

        System.out.println(answer);
    }

    //TODO 확인할 것 (3): DP를 활용한 DFS
    static int dfs(int n, int m, int count) {
        if (count == target.length()) {
            return 1;
        }
        if (dp[n][m][count] != -1) return dp[n][m][count];

        int num = 0;
        for(int i = 0; i < 4; i++) {
            for (int j = 1; j <= K; j++) {
                int nextN = n + dn[i] * j;
                int nextM = m + dm[i] * j;
                if (nextN >= 0 && nextM >= 0 && nextN < N && nextM < M) {
                    if (arr[nextN][nextM] == target.charAt(count)) {
                        num += dfs(nextN, nextM, count + 1);
                    }
                }
            }
        }
        return dp[n][m][count] = num;
    }
}
