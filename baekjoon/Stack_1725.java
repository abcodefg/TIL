package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//#1725 히스토그램
/*히스토그램 막대의 높이를 순서대로 입력받아 히스토그램 내부에서 가장 큰 직사각형의 넓이를 구하라.*/
public class Stack_1725 {
    /**
     * 히스토그램에서 가장 넓이가 큰 직사각형을 구하는 문제는 꽤 유명하다.
     * 접근방법으로는
     *  1.분할정복과 세그먼트 트리를 사용하는 풀이
     *  2.스택을 사용하는 풀이
     * 가 있는데 여기서는 스택을 사용하는 풀이를 기록하고자 한다.
     *
     * 문제를 읽고 스택으로 접근할 수 있다는 걸 떠올리는 게 풀이의 관건이다.
     * 예를 들어, 차례대로 2, 4, 1, 5를 입력받는다고 하자.
     * 처음 2를 입력받은 경우, 높이가 2인 사각형이 생긴다.
     * 다음 4를 입력받은 경우, 높이가 4인 사각형이 생기며,
     *      높이가 앞서 입력받은 막대의 높이인 2보다 크거나 같으므로 높이가 2인 사각형의 너비가 확장된다.
     * 다음 1을 입력받은 경우, 높이가 1인 사각형이 생긴다.
     *      그런데, 마지막으로 입력받은 사각형의 높이 4보다 작으므로 이 사각형의 너비는 더 이상 확장되지 않는다.
     *          따라서, 이는 더 이상 고려할 필요가 없으므로 높이가 4인 사각형의 최대 넓이를 구한 뒤 제거한다.
     *      그 다음으로 마지막으로 입력받은 2 역시 1보다 크므로 높이가 2인 사각형의 최대 넓이를 구한 뒤 제거한다.
     *
     * 이처럼 입력받은 막대를 포함하는 직사각형은 그 다음 막대의 높이가 크거나 같은 경우에 한해 우측으로 확장되고
     * 작은 경우, 입력받은 역순으로 최대 넓이를 구하고 제거된다.
     * 따라서, 스택을 이용하여 접근할 수 있다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] heights = new int[n];

        for(int i = 0; i < n; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        //스택에는 {막대의 인덱스, 막대의 높이}의 배열을 저장한다.
        Stack<int[]> stack = new Stack<>();
        int max = 0;

        //히스토그램의 막대를 차례대로 순회한다.
        for(int i = 0; i < heights.length; i++) {
            int curIdx = i;
            //스택에서 마지막으로 저장된 막대의 높이가 다음 막대의 높이보다 크다면
            while(!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                //해당 막대를 제거하고
                // 그 막대 전체가 포함된 가장 큰 직사각형의 넓이가 최대 넓이라면 저장한다.
                int idx = stack.peek()[0];
                int height = stack.peek()[1];
                stack.pop();

                int area = height * (i - idx);
                max = Math.max(max, area);
                //이 때, 다음 막대의 높이(n)는 제거된 막대의 높이보다 작으므로
                //높이가 n인 직사각형의 너비를 측정하기 위한 시작 인덱스를 갱신한다.
                // ex) 2 4 1 을 입력받으면 높이가 1인 직사각형은 0번 막대에서부터 그릴 수 있음
                curIdx = idx;
            }
            stack.push(new int[]{curIdx, heights[i]});
        }
        //스택에 남은 막대 각각에 대해 전체를 포함하는 직사각형의 최대 넓이를 구한다.
        while(!stack.isEmpty()) {
            int idx = stack.peek()[0];
            int height = stack.peek()[1];
            stack.pop();

            max = Math.max(max, height * (heights.length - idx));
        }
        System.out.println(max);
    }
}
