package programmers;

/*XX게임에는 피로도 시스템(0 이상의 정수로 표현합니다)이 있으며, 일정 피로도를 사용해서 던전을 탐험할 수 있습니다.
* 이때, 각 던전마다 탐험을 시작하기 위해 필요한 "최소 필요 피로도"와 던전 탐험을 마쳤을 때 소모되는 "소모 피로도"가 있습니다.
* 어떤 유저가 던전을 최대한 많이 탐험하고자 합니다.
* 유저의 현재 피로도 k와 각 던전별 "최소 필요 피로도", "소모 피로도"가 담긴 2차원 배열 dungeons 가 매개변수로 주어질 때,
* 유저가 탐험할수 있는 최대 던전 수를 return 하도록 solution 함수를 완성해주세요.
 */
public class BruteForce_피로도 {
    static int answer;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        dfs(dungeons, new boolean[dungeons.length], 0, k);
        return answer;
    }

    static void dfs(int[][] dungeons, boolean[] visited, int count, int k) {

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(dungeons, visited, count + 1, k - dungeons[i][1]);
                visited[i] = false;
            }
        }

        answer = Math.max(answer, count);
    }
}
