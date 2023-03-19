package programmers;

/*
* 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/118668
 */
public class DP_코딩_테스트_공부 {
    /**
     * 1. 첫 번째 시도 : 백트래킹
     * 각 단계에서 누적된 시간을 조기 종료 조건으로 설정한 완전탐색으로 접근했으나, 시간이 초과되었다.
     *
     * 2. 두 번째 시도 : dp
     * 배열에 (알고력, 코딩력)에 도달하기 위해 필요한 최단시간을 기록한다.
     * 인덱스 (alp,cop)에서 (알고력 최댓값, 코딩력 최댓값)까지 순회하며,
     * 각 상태에서 주어진 방법을 사용해 알고력 혹은 코딩력을 높인 다음 상태의 값을 수정하고
     * (알고력 최댓값, 코딩력 최댓값)의 값을 반환했다.
     *
     * 조금 특이한 유형의 bottom-up DP라 특히 어려웠다.
     * 처음에는 각 상태를 공부를 하거나 문제를 푼 이후의 상태로 취급하고
     * 현재 순회 중인 상태의 값을 과거 상태의 dp값으로부터 역추적하는 식으로 구현하려 했으나
     * 구현하거나 이해하기 어려울 뿐더러, 조건을 설정하거나 예외를 처리하기가 까다로웠다.
     */
    public int maxAl = 0, maxCo = 0, answer = 100;
    public int solution(int alp, int cop, int[][] problems) {

        for (int[] problem : problems) {
            maxAl = Math.max(maxAl, problem[0]);
            maxCo = Math.max(maxCo, problem[1]);
        }
        bf(0, alp, cop, problems);

        return answer;
    }

    public void bf(int cost, int alp, int cop, int[][] problems) {
        if (alp >= maxAl && cop >= maxCo) {
            answer = Math.min(cost, answer);
            return;
        }

        if (cost >= answer)
            return;

        if (alp < maxAl)
            bf(cost + 1, alp + 1, cop, problems);
        if (cop < maxCo)
            bf(cost + 1, alp, cop + 1, problems);

        for (int[] problem : problems) {
            if (alp >= problem[0] && cop >= problem[1]) {
                bf(cost + problem[4], alp + problem[2], cop + problem[3], problems);
            }
        }
    }

    public int solution2(int alp, int cop, int[][] problems) {
        int maxAl = 0, maxCo = 0;
        for (int[] problem : problems) {
            maxAl = Math.max(maxAl, problem[0]);
            maxCo = Math.max(maxCo, problem[1]);
        }

        // TODO Check (1) : 빠른 return 예외 처리
        //  문제 풀 때, 계산이 필요 없는 경우가 있는지 확인하고 바로 return하는 습관을 들이자
        // 초기의 알고력과 코딩력이 모든 문제를 풀 수 있는 수준이라면,
        //  모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간은 0이다.
        if (alp >= maxAl && cop >= maxCo)
            return 0;

        // TODO Check (2) : 예외 발생 방지
        //  초기의 알고력(코딩력)이 그 어떤 문제를 풀기 위해 필요한 알고력(코딩력)보다 높다면
        //  아래의 반복문에서 Array Index Out Of Bounds 예외가 발생할 수 있으므로 값을 변경
        alp = Math.min(alp, maxAl);
        cop = Math.min(cop, maxCo);

        // 알고력 i, 코딩력 j를 얻는 최단 시간을 기록하는 배열
        // 이후 최솟값으로 update하기 위해 (0,0)을 제외하고 가장 큰 정수값으로 초기화한다
        // 배열의 최솟값을 채우는 반복문에서 i = maxAl이거나 j = maxCo인 경우에 예외가 발생하지 않게 하기 위해
        //  배열의 크기를 한 칸 크게 설정한다
        int[][] dp = new int[maxAl + 2][maxCo + 2];
        for (int i = alp; i < dp.length; i++) {
            for (int j = cop; j < dp[i].length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[alp][cop] = 0;

        // TODO Check (3) : 각 단계의 최솟값/최댓값을 추적하는 BOTTOM-UP DP
        //  이전에 순회한 상태의 값에 추가로 누적하는 형태의 BOTTOM-UP DP와 구별된다
        //  특정 상태의 값을 인접한 상태의 값들만으로 계산할 수 있는 경우 특히 유용하다
        //   이 문제에서 인접한 상태란 이 문제에서는 주어진 방법을 통해 알고력/코딩력을 키우기 전의 상태를 의미한다고 볼 수 있다

        // TODO Check (4) : 이후 순회할 상태의 값을 수정(update)하는 BOTTOM-UP DP
        //  수정이 이루어지는 순서가 중요하지 않은 경우 사용할 수 있다
        //    이 문제의 경우 (i,j) 단계에서 (i+1,j)나 (i,j+1) 등의 값을 수정할 수 있다
        for (int i = alp; i <= maxAl; i++) {
            for (int j = cop; j <= maxCo; j++) {
                // 알고리즘/코딩 공부를 하는 경우
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                // 문제를 푸는 경우
                for (int[] problem : problems) {
                    if (i < problem[0] || j < problem[1])
                        continue;

                    // 현재 코딩력이 15이고 문제를 푼 후 얻을 수 있는 코딩력이 7인데,
                    // 모든 문제를 푸는 데 필요한 코딩력이 20이라고 하자.
                    // 문제를 풀고 난 후의 코딩력은 22와 같이 20보다 큰 코딩력 역시
                    // 모든 문제를 풀 수 있는 코딩력이므로 20으로 바꾸어 기록한다
                    int alResult = Math.min(i + problem[2], maxAl);
                    int coResult = Math.min(j + problem[3], maxCo);

                    dp[alResult][coResult] = Math.min(dp[alResult][coResult], dp[i][j] + problem[4]);
                }
            }
        }

        return dp[maxAl][maxCo];
    }
}
