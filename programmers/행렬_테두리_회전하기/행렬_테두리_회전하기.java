package programmers.행렬_테두리_회전하기;

public class 행렬_테두리_회전하기 {
    private int[][] nums;
    public int[] solution(int rows, int columns, int[][] queries) {
        nums = new int[rows + 1][columns + 1];
        int[] answer = new int[queries.length];

        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= columns; ++j) {
                nums[i][j] = (i - 1) * columns + j;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotateAndReturnSmallest(queries[i]);
        }

        return answer;
    }

    private int rotateAndReturnSmallest(int[] query) {
        // 순서대로 회전시킬 때 덮어씌워지는 값 미리 저장
        int topLeft = nums[query[0]][query[1]];
        int smallest = topLeft;

        for (int i = query[0]; i < query[2]; ++i) {
            nums[i][query[1]] = nums[i+1][query[1]];
            smallest = Math.min(smallest, nums[i][query[1]]);
        }

        for (int i = query[1]; i < query[3]; ++i) {
            nums[query[2]][i] = nums[query[2]][i + 1];
            smallest = Math.min(smallest, nums[query[2]][i]);
        }

        for (int i = query[2]; i > query[0]; --i) {
            nums[i][query[3]] = nums[i - 1][query[3]];
            smallest = Math.min(smallest, nums[i][query[3]]);
        }

        for (int i = query[3]; i > query[1]; --i) {
            nums[query[0]][i] = nums[query[0]][i - 1];
            smallest = Math.min(smallest, nums[query[0]][i]);
        }

        nums[query[0]][query[1] + 1] = topLeft;

        return smallest;
    }
}
