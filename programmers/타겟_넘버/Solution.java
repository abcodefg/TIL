package programmers.타겟_넘버;

public class Solution {
    private int target, len, cnt = 0;
    public int solution(int[] numbers, int target) {
        this.target = target;
        len = numbers.length;

        dfs1(0, 0, numbers);
        return cnt;

//        return dfs2(0, 0, numbers);
    }

    // 멤버 변수를 통해 카운트하는 풀이
    private void dfs1(int i, int sum, int[] numbers) {
        // 숫자를 모두 사용했다면
        if (i == len) {
            // 계산 결과가 타겟 넘버인 경우 카운트를 1 늘린다
            if (sum == target)
                cnt++;
            return;
        }

        dfs1(i + 1, sum + numbers[i], numbers);
        dfs1(i + 1, sum - numbers[i], numbers);
    }

    // 가짓수를 직접 반환하는 풀이
    private int dfs2(int i, int sum, int[] numbers) {
        if (i == len) {
            if (sum == target)
                return 1;
            else
                return 0;
        }

        return dfs2(i + 1, sum + numbers[i], numbers) + dfs2(i + 1, sum - numbers[i], numbers);
    }
}
