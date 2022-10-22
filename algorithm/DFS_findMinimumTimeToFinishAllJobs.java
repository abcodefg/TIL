package algorithm;

import java.util.Arrays;

/*각 일들을 마치는 데 필요한 시간을 담은 배열과 일꾼의 수를 입력받는다.
* 일꾼들에게 일을 분배한 후, 동시에 일을 시작하다고 할 때,
* 모든 일꾼이 일을 마치는 데 걸리는 최소의 시간을 구하라.
* 각 일꾼에게 할당된 업무들의 총 소요시간 중 최대값이 최소가 되도록 일이 분배되는 경우를 찾으면 된다.*/
public class DFS_findMinimumTimeToFinishAllJobs {
    /**
     * dfs, 백트래킹으로 접근했고 입력값 크기가 작을 때는 정답이 나왔으나,
     * 조건을 잘못 설정했는지, 입력값 크기가 큰 경우 실행시간이 초과되었다.
     * leetcode에 참고할 만한 좋은 풀이가 있어 기록한다.
     * <a href="https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/discuss/1009917/Clean-Java">...</a>
     */
    private int res = Integer.MAX_VALUE;
    public int minimumTimeRequired(int[] jobs, int k) {
        dfs(jobs, new int[k], jobs.length - 1, 0);
        return res;
    }

    private void dfs(int[] jobs, int[] workers, int pos, int curMax) {
        if (pos == -1) {
            res = Math.min(curMax, res);
            return;
        }
        if (curMax > res) return;
        for (int i = 0; i < workers.length; i++) {
            if (i > 0 && workers[i] == workers[i - 1]) continue; //reduce duplicate call
            workers[i] += jobs[pos];
            dfs(jobs, workers, pos - 1, Math.max(curMax, workers[i]));
            workers[i] -= jobs[pos];
        }
    }
}
